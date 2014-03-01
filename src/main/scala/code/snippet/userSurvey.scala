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
    "name=name" #> SHtml.text(name, name = _, "id" -> "the_name") &
    "name=phone_number" #> SHtml.text(phone_number, phone_number = _) &
    "type=submit" #> SHtml.onSubmitUnit(process)


  // process the form
  // Fixme save data to database
  private def process() = {
    ReporterInformation.Name.set(name)
    ReporterInformation.PhoneNumber.set(phone_number)
    ReporterInformation.DateOfReport.set(new Date)

    ReporterInformation.create

    S.redirectTo("/")
  }

}
