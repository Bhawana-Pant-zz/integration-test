package com.esure.integrationtest

import com.esure.integrationtest.request.RequestState
import io.restassured.RestAssured
import io.restassured.config.HttpClientConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.response.Response

import static com.esure.integrationtest.request.HttpMethod.GET
import static com.esure.integrationtest.request.HttpMethod.POST
import static io.restassured.RestAssured.given
import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT
import static org.apache.http.params.CoreConnectionPNames.SO_TIMEOUT

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
                        //.config(clientTimeoutConfig())
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

//    private RestAssuredConfig clientTimeoutConfig() {
//        return RestAssured.config()
//            .httpClient(HttpClientConfig.httpClientConfig()
//            .setParam(CONNECTION_TIMEOUT, 1000)
//            .setParam(SO_TIMEOUT, 1000))
//    }

}