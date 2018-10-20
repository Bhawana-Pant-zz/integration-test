package com.esure.integrationtest.cardpayment

import com.esure.integrationtest.ResponseState
import com.esure.integrationtest.ScenarioState
import com.esure.integrationtest.cardpayment.client.CardPaymentClient
import com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults
import spock.lang.Shared
import spock.lang.Specification

import static Scenarios.setupScenarioState
import static com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults.requestWithProductCode
import static org.apache.http.HttpStatus.SC_BAD_REQUEST
import static org.apache.http.HttpStatus.SC_OK

class SetUpSpec extends Specification {
    ScenarioState setupScenarioUnderTest
    @Shared def client = new CardPaymentClient()

    def setup() {
        setupScenarioUnderTest = setupScenarioState(client)
    }

    def "Setup is success with different product code EM and FC"(String productCode) {
        given: 'a requestState with product code $productCode'
        setupScenarioUnderTest.requestState().with(requestWithProductCode(productCode))

        when: 'setupScenarioState requestState is sent'
        ResponseState response = executeSetupAndGetResponse()

        then: 'it returns 200 responseState code'
        with(response) {
            response.printResponseBodyForDebugging()
            assert response.statusCode() == SC_OK
            assert response.firstValueAtPath('infos.message') == 'Falcon card payment setupScenarioState method called successfully'
            assert response.firstValueAtPath('results.reason') == 'ACCEPTED'
        }

        where:
        productCode | _
        'EM'        | _
        'FC'        | _
    }

    def "Set up validation fails when request has missing fields"() {
        given: 'a requestState with no dynamic data field'
        setupScenarioUnderTest.requestState().with(SetupRequestDefaults.requestWithNoDynamicData())

        when: 'setupScenarioState requestState is sent'
        ResponseState response = executeSetupAndGetResponse()


        then: 'it returns 400 responseState'
        with(response) {
            assert response.statusCode() == SC_BAD_REQUEST
            assert response.firstValueAtPath('errors.code') == 'BAD_REQUEST'
            assert response.firstValueAtPath('errors.message') == 'setupScenarioState.body.paymentSetupRequest.dynamicData must contain atleast 1 entry'
        }
    }

    private ResponseState executeSetupAndGetResponse() {
        setupScenarioUnderTest.sendRequest()
        def response = setupScenarioUnderTest.responseState()
        response
    }
}




