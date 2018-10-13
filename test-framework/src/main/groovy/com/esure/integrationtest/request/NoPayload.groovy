package com.esure.integrationtest.request

class NoPayload implements Payload {

    @Override
    boolean hasPayload() {
        return false
    }
}
