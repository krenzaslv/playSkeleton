package controllers

import javax.inject.{Inject, Singleton}

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
class Links extends Controller with MongoController {


  private final val logger = Logger


  def collection: JSONCollection = db.collection[JSONCollection]("links")


  import models._
  import models.JsonFormats._

  def createLink = Action.async(parse.json) {
    request =>
      request.body.validate[Link].map {
        link =>
          collection.insert(link).map {
            lastError =>
              logger.debug(s"Successfully inserted with LastError: $lastError")
              Created(s"Link Created")
          }
      }.getOrElse(Future.successful(BadRequest("invalid json")))
  }

  def findLinks = Action.async {
    val cursor: Cursor[Link] = collection.
      find(Json.obj()).
      cursor[Link]

    val futureLinkList: Future[List[Link]] = cursor.collect[List]()

    val futureLinkJsonArray: Future[JsArray] = futureLinkList.map { links =>
      Json.arr(links)
    }
    futureLinkJsonArray.map {
      links =>
        Ok(links(0))
    }
  }

}
