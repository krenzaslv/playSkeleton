package models

object JsonFormats {
   import play.api.libs.json.Json

   implicit val userFormat = Json.format[User]
   implicit val linkFormat = Json.format[Link]

 }