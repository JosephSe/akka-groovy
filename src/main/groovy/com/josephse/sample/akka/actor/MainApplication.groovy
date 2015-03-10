package com.josephse.sample.akka.actor

import akka.actor.ActorSystem
import akka.actor.Props
import static akka.pattern.Patterns.ask

class MainApplication {
    private def cl = this.class.classLoader

    public static void main(String[] a) {
        def system = ActorSystem.create("MainActorSystem")
        def requestManager = system.actorOf(Props.create(HttpRequestManager.class, "requestManager"))
        requestManager.tell((ask()))
    }
}
