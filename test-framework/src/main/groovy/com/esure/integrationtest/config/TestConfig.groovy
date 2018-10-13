package com.esure.integrationtest.config

class TestConfig {

    private static final ConfigLoader configLoader = new ConfigLoader()

    static ConfigObject getConfig() {
        configLoader.getConfig()
    }
}
