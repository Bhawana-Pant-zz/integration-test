package com.esure.integrationtest.cardpayment.payload.Enroll3DSecure

import com.esure.integrationtest.request.Payload
import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy

class Enroll3DSecureRequest implements Payload {
    ChannelSource channelSource
    ThreeDSEnrolRequest threeDSEnrolRequest
}
@Builder(builderStrategy = ExternalStrategy, forClass = Enroll3DSecureRequest)
class Enroll3DSecureBuilder{}

class ChannelSource implements Payload {
    String productCode, channel}

@Builder(builderStrategy = ExternalStrategy, forClass = ChannelSource)
class ChannelSourceBuilder{}

class ThreeDSEnrolRequest implements Payload {
    String avsAddress,amount,browserDeviceCategory,browserAcceptHeaders,esureReference,avsPostcode
    String cpaTransaction,pspReference,cardNumber,browserUserAgent
    URL merchantUrl
}
@Builder(builderStrategy = ExternalStrategy, forClass = ThreeDSEnrolRequest)
class ThreeDSEnrolRequestBuilder{}

class Enroll3DSecurerequestDefaults{
    static ChannelSourceBuilder defaultChanneSource(){
        return new ChannelSourceBuilder().channel("I").productCode("EM")
    }

    static ThreeDSEnrolRequestBuilder defaultThreeDSEnrolRequest(){
        return new ThreeDSEnrolRequestBuilder()
            .pspReference("4500204522303532")
            .avsAddress("The Observatory Castlefield Road")
            .amount("1000")
            .browserDeviceCategory("1")
            .esureReference("123-232")
            .avsPostcode("RH20SG")
            .cpaTransaction("true")
            .cardNumber("444433******1350")
            .merchantUrl('http://www.esure.com'.toURL())
            .browserUserAgent("chrome")
    }

    static Enroll3DSecureRequestDefaults(){
        return new Enroll3DSecureBuilder()
            .channelSource(defaultChanneSource().build())
            .threeDSEnrolRequest(defaultThreeDSEnrolRequest().build())
    }
}
