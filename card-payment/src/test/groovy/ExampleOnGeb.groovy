
import geb.Browser

System.setProperty('webdriver.chrome.driver','/Users/ahaan/repos/integration-test/card-payment/src/test/resources/webdriver/chromedriver')

Browser.drive{
    go "http://gebish.org"

    assert title == "Geb - Very Groovy Browser Automation"

    $("div.menu a.manuals").click()
    waitFor { !$("#manuals-menu").hasClass("animating") }

    $("#manuals-menu a")[0].click()

    assert title.startsWith("The Book Of Geb")

}
