package com.esure.integrationtest.config

class ConfigTester {

    public static void main(String[] args) {
        def config = new ConfigSlurper()
            .parse(getClass().getResource('/config.groovy'))
            .get('env')

        config
    }
}
