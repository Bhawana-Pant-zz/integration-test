import spock.lang.Specification

class FirstSpecification extends Specification {

    def "one plus one should equal two"() {
        expect:
        1 + 1 == 2
    }

    def "testing profile"() {
        given:
        def environment = System.getProperty('env')  == null ? 'dev' : System.getProperty('env')
        println "Environment is set to $environment"
    }

}
