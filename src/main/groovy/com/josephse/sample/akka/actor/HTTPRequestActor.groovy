package com.josephse.sample.akka.actor

import akka.actor.UntypedActor
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.impl.client.HttpClientBuilder

class HTTPRequestActor extends UntypedActor {
    private HttpClient client

    HTTPRequestActor() {
        this.client = HttpClientBuilder.create().build()

    }

    HTTPRequestActor(HttpClient client) {
        this.client = client
    }

    @Override
    void onReceive(Object message) throws Exception {
        switch (message) {
            case Request :
                Request req = (Request)message
                def response = client.execute(req.requestBase)
                sender.tell(new Response(req.id, response), self)
//                sender.tell(new Response(message.id, null), getSelf())
                break
            default:
                unhandled(message)
        }
    }
}
