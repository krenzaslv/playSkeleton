package controllers

import javax.inject.Singleton

import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api._

import scala.concurrent.Future

@Singleton
class Users extends Controller with MongoController {


  private final val logger = Logger

  def collection: JSONCollection = db.collection[JSONCollection]("users")


  import models.JsonFormats._
  import models._

  def createUser = Action.async(parse.json) {
    request =>
      request.body.validate[User].map {
        user =>
          collection.insert(user).map {
            lastError =>
              logger.debug(s"Successfully inserted with LastError: $lastError")
              Created(s"User Created")
          }
      }.getOrElse(Future.successful(BadRequest("invalid json")))
  }


  def findUsers = Action.async {
    val cursor: Cursor[JsObject] = collection.
      find(Json.obj("active" -> true)).
      sort(Json.obj("created" -> -1)).
      cursor[JsObject]

    val futureUsersList: Future[List[JsObject]] = cursor.collect[List]()

    val futurePersonsJsonArray: Future[JsArray] = futureUsersList.map { users =>
      Json.arr(users)
    }
    futurePersonsJsonArray.map {
      users =>
        Ok(users(0))
    }
  }

  def findUser(id: String) = Action.async {
    val cursor = collection.find(Json.obj("_id" -> Json.obj("$oid" -> id))).cursor[User]
    val futureUsersList: Future[List[User]] = cursor.collect[List]()
    val futurePersonsJsonArray: Future[User] = futureUsersList.map { users =>
      users.head
    }
    futurePersonsJsonArray.map {
      user =>
        Ok(userFormat.writes(user))
    }
  }

  def addLink(id: String) = Action.async(parse.json) {
    request =>
      request.body.validate[Link].map {
        string =>
          collection.update(Json.obj("_id" -> Json.obj("$oid" -> id)),
            Json.obj("$push" -> Json.obj("links" -> string))
          ).map {
            lastError =>
              logger.debug(s"Successfully updated with LastError: $lastError")
              Created(s"User Updated")
          }
      }.getOrElse(Future.successful(BadRequest("invalid json")))
  }


}