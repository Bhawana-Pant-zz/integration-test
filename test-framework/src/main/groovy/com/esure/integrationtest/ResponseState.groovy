package com.esure.integrationtest

import io.restassured.response.Response

class ResponseState {
    Response response

    void response(Response response) {
        this.response = response
    }

    int statusCode() {
        return response.getStatusCode()
    }

    String firstValueAtPath(String path) {
        return response.jsonPath().getList(path).get(0)
    }

    void printResponseBodyForDebugging() {
        response.body().prettyPrint()
    }
}
