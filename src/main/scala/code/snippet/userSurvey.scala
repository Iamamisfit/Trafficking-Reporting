package code 
package snippet 

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import net.liftweb.http._
import net.liftweb.common._
import net.liftweb.util.Helpers._
import code.model.ReporterInformation

class UserSurvey extends StatefulSnippet {

  // state unique to this instance of the stateful snippet
  private var name = ""
  private var phone_number = ""

  // StatefulSnippet requires an explicit dispatch
  //   // to the method.
  def dispatch = {case "render" => render}

  // associate behavior with each HTML element
  def render = 
    ("name=" + S ? "REPORTER_NAME") #> SHtml.text(name, name = _, "id" -> "the_name") &
    ("name=" + S ? "REPORTER_PHONE") #> SHtml.text(phone_number, phone_number = _) &
    "type=submit" #> SHtml.onSubmitUnit(process)


  // process the form
  // Fixme save data to database
  private def process() = {

    val reporter = ReporterInformation.create

    reporter.Name.set(name)
    reporter.PhoneNumber.set(phone_number)
    reporter.DateOfReport.set(new Date)


    reporter.save()

    S.redirectTo("/")
  }

}
