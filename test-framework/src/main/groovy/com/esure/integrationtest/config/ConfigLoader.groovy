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
        def environment = PropertyPermission.hasProperty('env') ? env : 'test'
        this.config = new ConfigSlurper()
            .parse(getClass().getResource('/config.groovy'))
            .get('env')[environment] as ConfigObject
    }
}
