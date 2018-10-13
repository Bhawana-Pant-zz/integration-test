package com.esure.integrationtest.cardpayment.client

import com.esure.integrationtest.Client
import com.esure.integrationtest.config.TestConfig


class CardPaymentClient implements Client {
    final String baseUri

    CardPaymentClient () {
        this.baseUri = TestConfig.getConfig()['url']
    }

    @Override
    String getBaseUri() {
        return this.baseUri + '/api-jva-cardpayment/v1/payment'
    }
}
