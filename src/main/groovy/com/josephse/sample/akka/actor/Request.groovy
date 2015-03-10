package com.josephse.sample.akka.actor

import groovy.transform.Immutable
import org.apache.http.HttpRequest
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpRequestBase

class Request {
    String id
    HttpRequestBase requestBase

    Request(String id, HttpRequest requestBase) {
        this.id = id
        this.requestBase = requestBase
    }
}

class Response {
    String id
    HttpResponse response

    Response(String id, HttpResponse response) {
        this.id = id
        this.response = response
    }
}
