package code.model

/**
 * Created by nick on 2/23/14.
 */


import net.liftweb.mapper._

class TraffickingReport extends LongKeyedMapper[TraffickingReport] with IdPK{

  def getSingleton = TraffickingReport

  //object id extends MappedLongIndex(this)
  object state extends MappedString(this,2)
  object country extends MappedString(this,100)
  object TypeOfTraffic extends MappedString(this,100)
  object NumberOfPeople extends MappedInt(this)
  object AverageAge extends MappedInt(this)
  object DescriptionOfTraffic extends MappedString(this, 300)
  object isThisOnline extends MappedBoolean(this)
  object urlOfOnline extends MappedString(this, 100)
  object dateOfReport extends MappedDateTime(this)

  //location

  // State
  // Country
  // TypeOfAbuse
  // NumberOfPeople
  // Age
  //
  // Description of Abuse
  // Did it take place Online
  // Url


}

object TraffickingReport extends TraffickingReport with LongKeyedMetaMapper[TraffickingReport] {

  override def fieldOrder = List(id, state, country, TypeOfTraffic, NumberOfPeople, AverageAge, DescriptionOfTraffic, isThisOnline, urlOfOnline)
}