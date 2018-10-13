package com.esure.integrationtest.request

class RequestState {
    HttpMethod httpMethod = HttpMethod.GET
    String path = ""
    Map<String, Object> headers = [:]
    Payload payload = new NoPayload()

    RequestState with(HttpMethod httpMethod) {
        this.httpMethod = httpMethod
        return this
    }

    RequestState path(String path) {
        this.path = path
        return this
    }

    RequestState headers(Map<String, Object> headers) {
        headers.clear()
        headers.entrySet().each { entry ->
            addHeader(entry.key, entry.value)
        }
        return this
    }

    RequestState addHeader(String key, Object value) {
        this.headers.put(key, value)
        return this
    }

    void with(Payload payload) {
        this.payload = payload
    }

    HttpMethod getHttpMethod() {
        return httpMethod
    }

    String getPath() {
        return path
    }

    Map<String, Object> getHeaders() {
        return headers
    }

    Payload getPayload() {
        return payload
    }
}