package com.esure.integrationtest.cardpayment

import com.esure.integrationtest.Client
import com.esure.integrationtest.ScenarioState
import com.esure.integrationtest.config.TestConfig
import org.apache.http.HttpHeaders
import org.apache.http.entity.ContentType

import static com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults.defaultSetupRequest
import static com.esure.integrationtest.request.HttpMethod.POST

class Scenarios {

    static ScenarioState defaultSetupScenario(Client client) {
        ScenarioState setupScenarioState = new ScenarioState(client)
        setupScenarioState.request()
            .with(POST)
            .path("/setup")
            .addHeader("apikey", TestConfig.getConfig()["apikey"])
            .addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())
            .with(defaultSetupRequest().build())

        setupScenarioState
    }

    static String executeSetupScenarioAndFetchUrl(Client client) {
        ScenarioState setupScenarioState = defaultSetupScenario(client)
        setupScenarioState.sendRequest()
        return setupScenarioState.response().firstValueAtPath('')
    }
}
