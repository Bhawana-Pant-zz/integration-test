package com.esure.integrationtest

import com.esure.integrationtest.request.RequestState
import io.restassured.RestAssured
import io.restassured.config.HttpClientConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.response.Response

import static com.esure.integrationtest.request.HttpMethod.GET
import static com.esure.integrationtest.request.HttpMethod.POST
import static io.restassured.RestAssured.given

trait Client {

    abstract  String getBaseUri()

    Response invoke(RequestState requestState) {
        RestAssured.baseURI = getBaseUri()

        try {
            switch (requestState.httpMethod) {
                case GET:
                    return given()
                        .headers(requestState.getHeaders())
                        .when()
                        .get(requestState.getPath())
                        .thenReturn()
                case POST:
                    return given()
                        .headers(requestState.getHeaders())
                        .body(requestState.getPayload().asJsonString())
                        .when()
                        .post(requestState.getPath())
                        .thenReturn()
                default:
                    throw new Exception("Not Implemented for method " + requestState.getHttpMethod().name())
            }
        } catch (SocketTimeoutException ex) {
            println "********* Socket timeout connecting to host " + getBaseUri() + "**************"
            throw ex
        }
    }



}