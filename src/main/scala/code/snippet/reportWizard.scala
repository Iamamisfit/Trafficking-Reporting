package code
package snippet

import net.liftweb._

import net.liftweb.common.Logger
import net.liftweb.util._
import net.liftweb.http._
import net.liftweb.wizard._
import java.io.File
import scala.io.Source
import code.model.TraffickingReport
import code.lib.ReportNotifier
import java.util.Date
//import com.github.nscala_time.time.Imports._

/**
 * Define the multi-page input screen
 */
object WizardExample extends Wizard with Logger{

  val statesURL = LiftRules.getResource("/data/us_states.csv").openOrThrowException("US State data missing!")

  val source = Source.fromURL(statesURL)
  val states = source.getLines.toSeq

  val traffickingList = Seq("Labor", "Sex")

  // define the first screen
  // Get Location information
  val screen1 = new Screen {

    val us_state = select(S ? "QUESTION_STATE", states.head, states)
    val us_country = field(S ? "QUESTION_CITY", "")
    val typeOfTrafficking = select(S ? "QUESTION_TRAFFICKING_TYPE", traffickingList.head, traffickingList)
    val numberOfVic = field("Number of:", 1, minVal(1, ""))

  }
  // define the second screen
  // Incident information
  val screen2 = new Screen {
    //
    val desp = textarea( S ? "QUESTION_DESCRIBE_SITUATION", "")

    // Was Online?
    val rad = radio(S ? "QUESTION_IS_ONLINE", S ? "ANSWER_NO", List(S ? "ANSWER_NO", S ? "ANSWER_YES"))
    // Url?
    val onlineLoc = field("Url of:", "")

    // here are password inputs with minimum length
    // return a List[FieldError]... there's an implicit conversion
    // from String to List[FieldError] that inserts the field's ID
    //def mustMatch(s: String): List[FieldError] =
    //  if (s != pwd1.is) "Passwords do not match" else Nil
  }



  def finish() {
    val report = TraffickingReport.create
    report.state.set(screen1.us_state)
    report.country.set(screen1.us_country)
    report.TypeOfTraffic.set(screen1.typeOfTrafficking)
    report.NumberOfPeople.set(screen1.numberOfVic)
    report.DescriptionOfTraffic.set(screen2.desp)
    report.DateOfReport.set(new Date)

    report.save()

    val reportId = report.id

    S.redirectTo("/thankyou.html?SITID=" + reportId)

    ReportNotifier.sendNotification()

  //  S.notice("Name: "+screen1.name)
  //  S.notice("Age: "+screen1.age)
  }
}
