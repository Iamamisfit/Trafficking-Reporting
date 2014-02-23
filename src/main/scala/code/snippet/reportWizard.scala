package code
package snippet

import net.liftweb._

import net.liftweb.util._
import net.liftweb.http._
import net.liftweb.wizard._
import java.io.File
import scala.io.Source
import code.model.TraffickingReport

/**
 * Define the multi-page input screen
 */
object WizardExample extends Wizard {

  //val resStates = LiftRules.getResource("/data/us_states.csv").openOrThrowException("US State data")
  // Need to load data from Files
  // http://stackoverflow.com/questions/1284423/read-entire-file-in-scala/1284446#1284446
  val stateList = Seq("AAA", "BBB", "CCC")
  val typeOfTrafficking = Seq("Labor", "Sex")
  //scala.io.Source.fromFile(resStates.getFile).getLines().toSeq

  // define the first screen
  // Get Location information
  val screen1 = new Screen {

    val us_state = select("What State?", "Someplace", stateList)
    val us_country = field("City", "")
    val typeOfAbuse = select("What Type? ", "Foo1", typeOfTrafficking)
    val numberOfVic = field("Number of:", 1, minVal(1, ""))

  }
  // define the second screen
  // Incident information
  val screen2 = new Screen {
    //
    val desp = textarea("Describe trafficking", "")

    // Was Online?
    val rad = radio("Did this event happen Online?", "No", List("Yes", "No"))
    // Url?
    val onlineLoc = field("Url of:", "")

    // here are password inputs with minimum lenght
    // return a List[FieldError]... there's an implicit conversion
    // from String to List[FieldError] that inserts the field's ID
    //def mustMatch(s: String): List[FieldError] =
    //  if (s != pwd1.is) "Passwords do not match" else Nil
  }



  def finish() {
    val report = new TraffickingReport

    report.state.set(screen1.us_state)
    report.country.set(screen1.us_country)
    report.TypeOfTraffic.set(screen1.typeOfAbuse)
    report.NumberOfPeople.set(screen1.numberOfVic)

    report.save()

  //  S.notice("Name: "+screen1.name)
  //  S.notice("Age: "+screen1.age)
  }
}
