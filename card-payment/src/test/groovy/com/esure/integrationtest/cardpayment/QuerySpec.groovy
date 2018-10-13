package com.esure.integrationtest.cardpayment

import com.esure.integrationtest.ScenarioState
import com.esure.integrationtest.cardpayment.client.CardPaymentClient
import spock.lang.Shared
import spock.lang.Specification

import static com.esure.integrationtest.cardpayment.Scenarios.setupScenario

class QuerySpec extends Specification {
    ScenarioState setupScenarioState
    ScenarioState queryScenarioState

    @Shared def client = new CardPaymentClient()

    def setup() {
        setupScenarioState = setupScenario(client)
        queryScenarioState = queryScenarioState(client)
    }
}
