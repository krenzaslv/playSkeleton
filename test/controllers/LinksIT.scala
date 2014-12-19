package controllers

import java.util.concurrent.TimeUnit

import org.specs2.Specification
import org.specs2.matcher.DataTables
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test._

import scala.concurrent._
import scala.concurrent.duration._

class LinksIT extends Specification with DataTables {
  def is =
    "Json validation testing with datatables" ! jsonDataTable


  val timeout: FiniteDuration = FiniteDuration(5, TimeUnit.SECONDS)


  def jsonDataTable =
    " Description"                      || "Request url"                                    | "response" |
      "Fails with int"                  !! 1                                                ! BAD_REQUEST |
      "Fails with invalid url"          !! "www.test! "                                     ! BAD_REQUEST |
      "Passes with valid url"           !! "http://www.google.ch"                           ! CREATED |
      "Passes with url without http://" !! "http://www.google.ch"                           ! CREATED|
      "Passes with more complex url"    !! "http://www.google.ch/?url=complex&test=complex" ! CREATED |> {
      (a, b, c) =>
        running(FakeApplication()) {
          val request = FakeRequest.apply(POST, "/link").withJsonBody(Json.obj(
            "url" -> a))
          val response = route(request)
          response.isDefined mustEqual true
          val result = Await.result(response.get, timeout)
          result.header.status must equalTo(c)
        }
    }
}