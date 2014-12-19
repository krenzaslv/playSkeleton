package controllers

import javax.inject.{Inject, Singleton}

import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._
import play.api.mvc._
import play.modules.reactivemongo.MongoController
import play.modules.reactivemongo.json.collection.JSONCollection
import reactivemongo.api.{Cursor, QueryOpts}
import reactivemongo.core.commands.Count
import services.UUIDGenerator

import scala.concurrent.Future

@Singleton
class Users extends Controller with MongoController {


  private final val logger = Logger

  def collection: JSONCollection = db.collection[JSONCollection]("users")

  import models._
  import models.JsonFormats._

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
    val cursor: Cursor[User] = collection.
      find(Json.obj("active" -> true)).
      sort(Json.obj("created" -> -1)).
      cursor[User]

    val futureUsersList: Future[List[User]] = cursor.collect[List]()

    val futurePersonsJsonArray: Future[JsArray] = futureUsersList.map { users =>
      Json.arr(users)
    }
    futurePersonsJsonArray.map {
      users =>
        Ok(users(0))
    }
  }

}
