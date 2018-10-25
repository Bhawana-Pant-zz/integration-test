package com.esure.integrationtest.cardpayment.payload.Query

import com.esure.integrationtest.request.Payload
import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy

class QueryRequest {
    ChannelSource channelSource
    PaymentQueryRequest paymentQueryRequest
}
@Builder(builderStrategy = ExternalStrategy , forClass = QueryRequest)
class QueryRequestBuilder{}

class ChannelSource implements Payload{
    String productCode,channel
}

@Builder(builderStrategy = ExternalStrategy, forClass = ChannelSource)
class ChannelSourceBuilder{}

class PaymentQueryRequest implements Payload{
    String pspReference
}
@Builder(builderStrategy = ExternalStrategy, forClass = PaymentQueryRequest)
class PaymentQueryRequestBuilder{}

class QueryRequestDefaults{
    static ChannelSourceBuilder defaultsChannelSource(){
        return new ChannelSourceBuilder().channel("I").productCode("EM")
    }
    static PaymentQueryRequestBuilder defaultPaymentQueryRequest(){
        return new PaymentQueryRequestBuilder().pspReference("")
    }
    static QueryRequest defaultQueryRequest(){
        return new QueryRequestBuilder()
            .channelSource(defaultsChannelSource().build())
            .paymentQueryRequest(defaultPaymentQueryRequest().build())
    }
}
