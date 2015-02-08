package models

import reactivemongo.bson.BSONObjectID


case class User(_id: Option[BSONObjectID],
                age: Int,
                firstName: String,
                lastName: String,
                active: Boolean,
                links: Option[List[Link]])

case class Link(url: String)

object JsonFormats {

  import play.api.libs.json._
  import play.modules.reactivemongo.json.BSONFormats._

  implicit val linkFormat = Json.format[Link]
  implicit val userFormat = Json.format[User]
}