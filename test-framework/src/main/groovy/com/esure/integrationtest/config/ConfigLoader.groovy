package com.esure.integrationtest.config

class ConfigLoader {
    private ConfigObject config

    ConfigLoader() {
        loadConfig()
    }

    ConfigObject getConfig() {
        return this.config
    }

    private void loadConfig() {
        def environment = isNullOrEmpty() ? 'dev' : System.getProperty('env')
        println "********************** Environment is set to $environment *************************"
        this.config = new ConfigSlurper()
            .parse(getClass().getResource('/config.groovy'))
            .get('env')[environment] as ConfigObject
    }

    private boolean isNullOrEmpty() {
        System.getProperty('env') == null || System.getProperty('env').trim().isEmpty()
    }
}
