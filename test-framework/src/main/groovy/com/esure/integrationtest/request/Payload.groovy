package com.esure.integrationtest.request

import groovy.json.JsonOutput

trait Payload {
    boolean hasPayload() {
        return true
    }

    String asJsonString() {
        return JsonOutput.toJson(this)
    }
}