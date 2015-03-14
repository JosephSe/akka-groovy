package com.josephse.sample.akka.actor

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.JavaTestKit
import org.apache.http.HttpRequest
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import scala.concurrent.duration.Duration

import static org.junit.Assert.assertNotNull

class HttpRequestTest {

    static ActorSystem system
    static HttpClient httpClient

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create()
        httpClient = HttpClientBuilder.create().build()
    }

    @Test
    public void testRecieve() {
        new JavaTestKit(system) {
            {
                final Props props = Props.create(HTTPRequestActor.class, httpClient)
                final ActorRef underTestActor = system.actorOf(props)
                final JavaTestKit probe = new JavaTestKit(system)

                underTestActor.tell(new Request("1", getRequest()), getRef())
                Response response = receiveOne(Duration.apply("1 second"));
                assertNotNull(response)
                assertNotNull(response.response.getEntity())
            }
        }
    }

    @AfterClass
    public static void tearDown() {
        JavaTestKit.shutdownActorSystem(system)
        system = null
    }

    private HttpRequest getRequest() {
        HttpGet get = new HttpGet("http://google.co.uk")
        return get
    }
}
