package com.esure.integrationtest.request

class RequestState {
    HttpMethod httpMethod = HttpMethod.GET
    String path = ""
    Map<String, Object> headers = [:]
    Payload payload = new NoPayload()

    RequestState with(HttpMethod httpMethod) {
        this.httpMethod = httpMethod
        this
    }

    RequestState path(String path) {
        this.path = path
        this
    }

    RequestState headers(Map<String, Object> headers) {
        headers.clear()
        headers.entrySet().each { entry ->
            addHeader(entry.key, entry.value)
        }
        this
    }

    RequestState addHeader(String key, Object value) {
        this.headers.put(key, value)
        this
    }

    void with(Payload payload) {
        this.payload = payload
    }

    HttpMethod getHttpMethod() {
        httpMethod
    }

    String getPath() {
        path
    }

    Map<String, Object> getHeaders() {
        headers
    }

    Payload getPayload() {
        payload
    }
}