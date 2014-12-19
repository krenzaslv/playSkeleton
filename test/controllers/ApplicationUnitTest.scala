package controllers

import org.specs2.mock.Mockito
import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._
import services.UUIDGenerator
import java.util.UUID

class ApplicationUnitTest extends Specification with Mockito {
  
  "Application" should {
    
    "invoke the UUID generator" in {
      val uuidGenerator = mock[UUIDGenerator]
      val application = new controllers.Application(uuidGenerator)

      uuidGenerator.generate returns UUID.randomUUID()

      application.randomUUID(FakeRequest())

      there was one(uuidGenerator).generate
    }
  }
}