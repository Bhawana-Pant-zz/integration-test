package com.esure.integrationtest.request

import groovy.json.JsonOutput

trait Payload {
    boolean hasPayload() {
         true
    }

    String asJsonString() {
         JsonOutput.toJson(this)
    }
}