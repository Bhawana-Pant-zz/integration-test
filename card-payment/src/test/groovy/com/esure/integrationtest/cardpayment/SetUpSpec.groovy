package com.esure.integrationtest.cardpayment

import com.esure.integrationtest.ResponseState
import com.esure.integrationtest.ScenarioState
import com.esure.integrationtest.cardpayment.client.CardPaymentClient
import com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults
import spock.lang.Shared
import spock.lang.Specification

import static Scenarios.defaultSetupScenario
import static com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults.requestWithProductCode
import static org.apache.http.HttpStatus.SC_BAD_REQUEST
import static org.apache.http.HttpStatus.SC_OK

class SetUpSpec extends Specification {
    ScenarioState scenarioUnderTest
    @Shared def client = new CardPaymentClient()

    def setup() {
       scenarioUnderTest = defaultSetupScenario(client)
    }

    def "Setup is success with different product code EM and FC"(String productCode) {
        given: 'a request with product code $productCode'
        scenarioUnderTest.request().with(requestWithProductCode(productCode))

        when: 'scenarioUnderTest request is sent'
        ResponseState response = executeSetupAndGetResponse()

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
        scenarioUnderTest.request().with(SetupRequestDefaults.requestWithNoDynamicData())

        when: 'scenarioUnderTest request is sent'
        ResponseState response = executeSetupAndGetResponse()


        then: 'it returns 400 response'
        with(response) {
            assert response.statusCode() == SC_BAD_REQUEST
            assert response.firstValueAtPath('errors.code') == 'BAD_REQUEST'
            assert response.firstValueAtPath('errors.message') == 'setup.body.paymentSetupRequest.dynamicData must contain atleast 1 entry'
        }
    }

    private ResponseState executeSetupAndGetResponse() {
        scenarioUnderTest.sendRequest()
        def response = scenarioUnderTest.response()
        response
    }
}




