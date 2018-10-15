package com.esure.integrationtest.cardpayment

import com.esure.integrationtest.ScenarioState
import com.esure.integrationtest.cardpayment.client.CardPaymentClient
import com.esure.integrationtest.cardpayment.iFrame.GetTheIFrame
import spock.lang.Shared
import spock.lang.Specification

import static com.esure.integrationtest.cardpayment.Scenarios.executeSetupScenarioAndFetchUrl

class QuerySpec extends Specification {
    ScenarioState queryScenarioState

    @Shared def client = new CardPaymentClient()

    def setup() {
        queryScenarioState = queryScenarioState(client)
    }

    def "test flow for query spec"() {
        given:
        String url = executeSetupScenarioAndFetchUrl(client)
        GetTheIFrame.submitValueToIFrame(url)



        expect: ""
    }
}

