package com.josephse.sample.akka.actor

import akka.actor.ActorRef
import static akka.pattern.Patterns.ask
import akka.actor.Props
import akka.actor.UntypedActor
import akka.routing.RoundRobinPool
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpRequestBase

class HttpRequestManager extends UntypedActor {

    private ActorRef httpRequestActors

    HttpRequestManager(int requestActorPoolSize) {
        httpRequestActors = context.actorOf(Props.create(HTTPRequestActor).withRouter(new RoundRobinPool(requestActorPoolSize)))
    }

    @Override
    void onReceive(Object message) throws Exception {
        switch (message) {
            case HttpRequestBase:
                httpRequestActors.tell(ask(message, getSender()))
                break
            unhandled(message)
        }
    }
}
