package com.esure.integrationtest

import com.esure.integrationtest.request.HttpMethod
import com.esure.integrationtest.request.RequestState
import io.restassured.response.Response

class ScenarioState {
    final Client client
    final RequestState requestState
    final ResponseState responseState

    ScenarioState(Client client) {
        this.client = client
        this.requestState = new RequestState()
        this.responseState = new ResponseState()
    }

    RequestState request() {
        return requestState
    }

    ResponseState response() {
        return responseState
    }

    void sendRequest() throws Exception {
        Response response = client.invoke(requestState)
        responseState.response(response)
    }
}
