/*
 * Copyright 2020 me.yangbajing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yangbajing.nacos4s.play.ws.javadsl

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import akka.stream.SystemMaterializer
import org.scalatest.wordspec.AnyWordSpecLike
import play.libs.ws.ahc.StandaloneAhcWSClient
import play.shaded.ahc.org.asynchttpclient.DefaultAsyncHttpClient

import scala.compat.java8.FutureConverters._

class NacosWSClientTest extends ScalaTestWithActorTestKit with AnyWordSpecLike {
  "NacosWSClient" should {
    "WSClient" in {
      // #NacosWSClientTest
      val ahcWSClient = new StandaloneAhcWSClient(new DefaultAsyncHttpClient(), SystemMaterializer(system).materializer)
      val wsClient = new NacosWSClient(system, ahcWSClient)
      val response = wsClient.url("https://github.com/yangbajing/nacos-sdk-scala").get().toScala.futureValue
      response.getStatus shouldBe 200
      wsClient.close()
      // #NacosWSClientTest
    }
  }
}
