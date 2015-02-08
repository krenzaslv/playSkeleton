package controllers

import com.github.athieriot.EmbedConnection
import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import play.api.test.Helpers._
import play.api.test._

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in {
      running(FakeApplication(withoutPlugins = Seq("play.modules.reactivemongo.ReactiveMongoPlugin"))) {
        route(FakeRequest(GET, "/boum")) must beNone
      }
    }

    "render the index page" in {
      running(FakeApplication(withoutPlugins = Seq("play.modules.reactivemongo.ReactiveMongoPlugin"))) {
        val home = route(FakeRequest(GET, "/")).get

        status(home) must equalTo(OK)
        contentType(home) must beSome.which(_ == "text/html")
        contentAsString(home) must contain ("Sample play app")
      }
    }
  }

}