package models


case class User(uuid: String,
                age: Int,
                firstName: String,
                lastName: String,
                active: Boolean,
                links: Option[List[Link]]
                 )

case class Link(url: String)

object JsonFormats {

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads._
  import play.api.libs.json._

  val userReads: Reads[User] = (
    (JsPath \ "uuid").read[String] and
      (JsPath \ "age").read[Int](min(0) keepAnd max(150)) and
      (JsPath \ "firstName").read[String](minLength[String](2)) and
      (JsPath \ "lastName").read[String](minLength[String](2)) and
      (JsPath \ "active").read[Boolean] and
      (JsPath \ "links").readNullable[List[Link]]
    )(User.apply _)

  val userWrites: Writes[User] = (
    (JsPath \ "uuid").write[String] and
      (JsPath \ "age").write[Int] and
      (JsPath \ "firstName").write[String] and
      (JsPath \ "lastName").write[String] and
      (JsPath \ "active").write[Boolean] and
      (JsPath \ "links").writeNullable[List[Link]]
    )(unlift(User.unapply))

  implicit val userFormat: Format[User] =
    Format(userReads, userWrites)


  implicit val linkFormat = Json.format[Link]
}