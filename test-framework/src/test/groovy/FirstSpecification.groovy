import spock.lang.Specification

class FirstSpecification extends Specification {

    def "one plus one should equal two"() {
        RestAssured.baseURI
        expect:
        1 + 1 == 2
    }
}
