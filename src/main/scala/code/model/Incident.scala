package code.model

/**
 * Created by nick on 2/22/14.
 */

import net.liftweb.mapper._

class Incident extends LongKeyedMapper[Incident] with IdPK{

  def getSingleton = Incident

  object name extends MappedString(this,100)

}

object Incident extends Incident with LongKeyedMetaMapper[Incident] {

  override def fieldOrder = List(name)
}
