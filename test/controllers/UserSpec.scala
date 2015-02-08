package controllers

import java.util.concurrent.TimeUnit

import com.github.athieriot.EmbedConnection
import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent._
import scala.concurrent.duration._

@RunWith(classOf[JUnitRunner])
class UserSpec extends Specification with EmbedConnection {

  val timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)

  "Users" should {

    "insert a valid json" in {
      running(FakeApplication()) {
        val request = FakeRequest.apply(POST, "/user").withJsonBody(Json.obj(
          "firstName" -> "Jack",
          "lastName" -> "London",
          "age" -> 27,
          "active" -> true))
        val response = route(request)
        response.isDefined mustEqual true
        val result = Await.result(response.get, timeout)
        result.header.status must equalTo(CREATED)
      }
    }

    "fail inserting a non valid json" in {
      running(FakeApplication()) {
        val request = FakeRequest.apply(POST, "/user").withJsonBody(Json.obj(
          "firstName" -> 98,
          "lastName" -> "London",
          "age" -> 27))
        val response = route(request)
        response.isDefined mustEqual true
        val result = Await.result(response.get, timeout)
        contentAsString(response.get) mustEqual "invalid json"
        result.header.status mustEqual BAD_REQUEST
      }
    }

  }

  override def embedConnectionPort: scala.Int = {
    return 27017;
  }
}