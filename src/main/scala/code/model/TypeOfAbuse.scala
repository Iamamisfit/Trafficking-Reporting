package code.model

/**
 * Created by nick on 2/22/14.
 */

import net.liftweb.mapper._

class TypeOfAbuse extends LongKeyedMapper[TypeOfAbuse] with IdPK{

  def getSingleton = TypeOfAbuse


  object name extends MappedString(this,100)
}

object TypeOfAbuse extends TypeOfAbuse with LongKeyedMetaMapper[TypeOfAbuse] {

  override def fieldOrder = List(name)
}
