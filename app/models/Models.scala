package models

import reactivemongo.bson.BSONObjectID
import play.modules.reactivemongo.json.BSONFormats._

case class User(_id: Option[BSONObjectID],
                age: Int,
                firstName: String,
                lastName: String,
                active: Boolean,
                links: List[Link])

case class Link(url: String)

object JsonFormats {

  import play.api.libs.json.Json

  implicit val linkFormat = Json.format[Link]
  implicit val userFormat = Json.format[User]
//  implicit val bsonIdFormat = Json.format[BSONObjectID]

  // val linkForm = Form(mapping("url" -> nonEmptyText(minLength = 3))(Link.apply _)(Link.unapply _))

  /* val userForm = Form(
     mapping(
       "id" -> optional(of[String]),
       "age" -> number,
       "firstName" -> text,
       "lastName" -> text,
       "active" -> boolean,
       "links" -> list(linkForm.mapping))(User.apply _)(User.unapply _))
 */
}