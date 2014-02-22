package code.model

/**
 * Created by nick on 2/22/14.
 */


import net.liftweb.mapper._

class US_State extends LongKeyedMapper[US_State] with IdPK{

  def getSingleton = US_State

  object name extends MappedString(this,100)
}

object US_State extends US_State with LongKeyedMetaMapper[US_State] {

  override def fieldOrder = List(name)
}

