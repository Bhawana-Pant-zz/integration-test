package com.esure.integrationtest.cardpayment

import com.esure.integrationtest.ScenarioState
import com.esure.integrationtest.cardpayment.client.CardPaymentClient
import com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults
import com.esure.integrationtest.config.TestConfig
import org.apache.http.HttpHeaders
import org.apache.http.entity.ContentType
import spock.lang.Shared
import spock.lang.Specification

import static com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults.defaultChannelSource
import static com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults.defaultSetupRequest
import static com.esure.integrationtest.cardpayment.payload.SetupRequestDefaults.defaultSetupRequest
import static com.esure.integrationtest.request.HttpMethod.POST

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

    def "Set up success message should return for EM and I"() {
        given:
        scenarioState.request().with(defaultSetupRequest().build())

        when: 'RequestState is sent to the service with product code EM and I'
        scenarioState.sendRequest()
        def response = scenarioState.response()

        then: 'should get the success response from the service'
        with(response) {
            assert response.statusCode() == 200
        }
    }

    def "Set up success message should return for FC and I"() {
        given:
        scenarioState.request().with(
            defaultSetupRequest().channelSource(
                defaultChannelSource().productCode('FC').build()).build())

        when: 'RequestState is sent to the service with product code EM and I'
        scenarioState.sendRequest()
        def response = scenarioState.response()


        then: 'should get the success response from the service'
        with(response) {
            assert response.status == 200
            assert response.getData().infos[0].message == 'Falcon card payment setup method called successfully'
            assert response.getData().results[0].reason == 'ACCEPTED'
        }
    }
//
//    def "Set up validation - Dynamic data is missing in the request"() {
//        given:
//        client.handler.failure = client.handler.success
//        when: 'RequestState is sent to the service with missing dynamic data field'
//        response = client.post(
//                headers: headers,
//                requestContentType: ContentType.JSON,
//                body: SetupRequestDefaults.defaultSetupRequest()
//                        .paymentSetupRequest(
//                        SetupRequestDefaults.defaultPaymentSetupRequest()
//                                .dynamicData(Collections.emptyList()).build()).build())
//
//        then: 'should get BAD_REQUEST in the response and an error message'
//        with(this.response) {
//            assert this.response.status == 400
//            assert this.response.getData().errors[0].code == 'BAD_REQUEST'
//            assert this.response.getData().errors[0].message == 'setup.body.paymentSetupRequest.dynamicData must contain atleast 1 entry'
//        }
//    }


}




