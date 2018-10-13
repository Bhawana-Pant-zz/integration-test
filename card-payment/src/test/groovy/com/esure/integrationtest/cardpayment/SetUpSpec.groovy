package com.esure.integrationtest.cardpayment

import com.esure.integrationtest.ScenarioState
import com.esure.integrationtest.cardpayment.client.CardPaymentClient
import com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults
import com.esure.integrationtest.config.TestConfig
import org.apache.http.HttpHeaders
import org.apache.http.entity.ContentType
import spock.lang.Shared
import spock.lang.Specification

import static com.esure.integrationtest.request.HttpMethod.POST
import static org.apache.http.HttpStatus.SC_BAD_REQUEST
import static org.apache.http.HttpStatus.SC_OK

class SetUpSpec extends Specification {
    ScenarioState scenarioState
    @Shared def client = new CardPaymentClient()

    def setup() {
        scenarioState = new ScenarioState(client)
        scenarioState.request()
            .with(POST)
            .path("/setup")
            .addHeader("apikey", TestConfig.getConfig()["apikey"])
            .addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
    }

    def "Setup is success with different product code EM and FC"(String productCode) {
        given: 'a request with product code $productCode'
        scenarioState.request().with(SetupRequestDefaults.requestWithProductCode(productCode))

        when: 'setup request is sent'
        scenarioState.sendRequest()
        def response = scenarioState.response()

        then: 'it returns 200 response code'
        with(response) {
            response.printResponseBodyForDebugging()
            assert response.statusCode() == SC_OK
            assert response.firstValueAtPath('infos.message') == 'Falcon card payment setup method called successfully'
            assert response.firstValueAtPath('results.reason') == 'ACCEPTED'
        }

        where:
        productCode | _
        'EM'        | _
        'FC'        | _
    }

    def "Set up validation fails when request has missing fields"() {
        given: 'a request with no dynamic data field'
        scenarioState.request().with(SetupRequestDefaults.requestWithNoDynamicData())

        when: 'setup request is sent'
        scenarioState.sendRequest()
        def response = scenarioState.response()

        then: 'it returns 400 response'
        with(response) {
            assert response.statusCode() == SC_BAD_REQUEST
            assert response.firstValueAtPath('errors.code') == 'BAD_REQUEST'
            assert response.firstValueAtPath('errors.message') == 'setup.body.paymentSetupRequest.dynamicData must contain atleast 1 entry'
        }
    }


}




