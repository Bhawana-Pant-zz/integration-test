package com.esure.integrationtest.cardpayment.client

import com.esure.integrationtest.Client
import com.esure.integrationtest.config.TestConfig


class CardPaymentClient implements Client {

    String baseUri

    CardPaymentClient () {
        this.baseUri = TestConfig.getConfig()['url']
    }

    @Override
    String getBaseUri() {
//        return "http://google.com"
        return this.baseUri
    }
}
