package com.esure.integrationtest.cardpayment.payload

import com.esure.integrationtest.request.Payload
import groovy.json.JsonOutput
import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy

import static java.util.Arrays.asList

class SetupRequest implements Payload {
    ChannelSource channelSource
    PaymentSetupRequest paymentSetupRequest

    String toJson() {
        return JsonOutput.toJson(this)
    }
}

@Builder(builderStrategy= ExternalStrategy, forClass= SetupRequest)
class SetupRequestBuilder {}

class ChannelSource implements Payload {
    String productCode, channel
}

@Builder(builderStrategy= ExternalStrategy, forClass= ChannelSource)
class ChannelSourceBuilder{}

class PaymentSetupRequest implements Payload {
    String esureReferenceId, callbackSuccessURL, callbackExpiryURL
    Integer amount
    List<String> dynamicData
}

@Builder(builderStrategy= ExternalStrategy, forClass= PaymentSetupRequest)
class PaymentSetupRequestBuilder {}

class SetupRequestDefaults {

    static SetupRequestBuilder defaultSetupRequest() {
        return new SetupRequestBuilder()
                .channelSource(defaultChannelSource().build())
                .paymentSetupRequest(defaultPaymentSetupRequest().build())
    }
    


    static ChannelSourceBuilder defaultChannelSource() {
        return new ChannelSourceBuilder().productCode("EM").channel("I")
    }

    static PaymentSetupRequestBuilder defaultPaymentSetupRequest() {
        return new PaymentSetupRequestBuilder()
                .esureReferenceId("432-245")
                .amount(1234)
                .callbackSuccessURL("https://www.esure.com/return_url")
                .callbackExpiryURL("https://www.esure.com/expiry_url")
                .dynamicData(asList(
                "<link rel='stylesheet' type='text/css' href='http://www.esure.com/wcm/groups/visual/documents/webasset/qb_master.css' media='all' /><link rel='stylesheet' type='text/css' href='http://www.esure.com/wcm/groups/visual/documents/webasset/qb_motor.css' media='all' /><link rel='stylesheet' type='text/css' href='http://www.esure.com/wcm/groups/visual/documents/webasset/qb_esure.css' />",
                "<p id='cpa_text'>This is where the text goes that tells you about CPA</p>"))
    }

}