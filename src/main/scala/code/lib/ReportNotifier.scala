/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package code.lib

import net.liftweb.common.Box
import net.liftweb.common.Full
import net.liftweb.util.Mailer
import net.liftweb.util.Mailer._
import javax.mail.{Authenticator,PasswordAuthentication}
import net.liftweb.util.Props
import scala.xml.NodeSeq

object ReportNotifier {

   def sendNotification(	) {
	 new EmailNotification()  
   }
}

/**
 * 
 * 
 * Standard Properties
mail.transport.protocol=smtp
mail.smtp.host=smtp.gmail.com
mail.smtp.port=587
mail.smtp.starttls.enable=true
mail.smtp.auth=true
mail.user=email@address.com
mail.password=password
 * 
 * Message Properties
notify.email.subject = "New Report"
notify.email.to   = 
notify.email.from = 
 * 
 */
class EmailNotification() {
 var isAuth = Props.get("mail.smtp.auth", "false").toBoolean
 val fromAddress = Props.get("notify.email.from")
 val toAddress   = Props.get("notify.email.to")
 val mailSubject = Props.get("notify.email.subject", "New Report")
 val message = <body>New Report</body>

sendEMail(fromAddress, toAddress, mailSubject, message)


private def sendEMail(from:Box[String], to: Box[String], subject: String, html: NodeSeq) {
  for(
   f <- from;
   t <- to
   ) Mailer.sendMail(From(f), Subject(subject),
    (XHTMLMailBodyType(html) :: To(t) :: ReplyTo("") :: Nil) : _*)
}

	
 Mailer.customProperties = Props.get("mail.smtp.host", "localhost") match {
  case "smtp.gmail.com" =>
    isAuth = true
    Map(
      "mail.smtp.host" -> "smtp.gmail.com",
      "mail.smtp.port" -> "587",
      "mail.smtp.auth" -> "true",
      "mail.smtp.starttls.enable" -> "true")
  case host => Map(
    "mail.smtp.host" -> host,
    "mail.smtp.port" -> Props.get("mail.smtp.port", "25"),
    "mail.smtp.auth" -> isAuth.toString
  )
 }

 if (isAuth) {
  (Props.get("mail.user"), Props.get("mail.password")) match {
    case (Full(username), Full(password)) =>
      Mailer.authenticator = Full(new Authenticator() {
        override def getPasswordAuthentication = new
            PasswordAuthentication(username, password)
      })
    case _ => new Exception("Username/password not supplied for Mailer.")
  }
 }
}

