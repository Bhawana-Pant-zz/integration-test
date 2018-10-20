import geb.Browser
import spock.lang.Specification

class ExampleOnGeb1 extends Specification{
    def setup(){
        System.setProperty('webdriver.chrome.driver', '/Users/ahaan/repos/integration-test/card-payment/src/test/resources/webdriver/chromedriver')
    }

    def "Enter Value to the iFrame"() {
        given:
        Browser browser = new Browser()

        when:
        browser.drive {
            go "http://gebish.org"

            assert title == "Geb - Very Groovy Browser Automation"

            $("div.menu a.manuals").click()
            waitFor { !$("#manuals-menu").hasClass("animating") }

            $("#manuals-menu a")[0].click()

        }
        then: {}
    }
}
