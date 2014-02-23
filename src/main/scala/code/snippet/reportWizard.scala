package code
package snippet

import net.liftweb._

import net.liftweb.util._
import net.liftweb.http._
import net.liftweb.wizard._

/**
 * Define the multi-page input screen
 */
object WizardExample extends Wizard {
  // define the first screen
  // Get Location information
  val screen1 = new Screen {

    val us_state = select("What State?", "Someplace", List("Out", "In", "Next door"))
    val us_country = field("City", "")
    val typeOfAbuse = select("What Type? ", "Foo1", List("Bar1", "Bar2", "Bar3"))
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
  //  S.notice("Name: "+screen1.name)
  //  S.notice("Age: "+screen1.age)
  }
}
