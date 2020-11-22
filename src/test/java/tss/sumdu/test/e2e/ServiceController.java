package tss.sumdu.test.e2e;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tss.sumdu.UrlTesterApp;

import static tss.sumdu.test.utils.SeleniumHelper.*;

public class ServiceController {

    static WebDriver driver;
    static UrlTesterApp app = null;


    @Before
    public void initDriver() {
        if (driver == null) {
            driver = chromeDriver(false);
        }
    }

    @Before
    public void initApp() {
        if (app == null) {
            app = new UrlTesterApp();
            app.start(UrlTesterApp.PORT);
        }
    }

    @After
    public void finish() {
        if (app != null) {
            app.stop();
            app = null;
        }
        if (app != null) {
            driver.close();
            driver = null;
        }
    }

    @Given("^the user is on ([\\S]*) page$")
    public void goToPage(String url) throws Throwable {
        driver.get(UrlTesterApp.URL + url);
    }

    @When("^he enters \"([^\"]*)\" as (\\w*)$")
    public void heEntersAsUrl(String text, String name) throws Throwable {
        WebElement element = elementByName(driver, name);
        element.clear();
        element.sendKeys(text);
    }

    @And("^he click on \"([^\"]*)\" button$")
    public void heClickOnButton(String name) throws Throwable {
        buttonByName(driver, name).click();
    }

    @Then("^ensure the service added with url \"([^\"]*)\", message \"([^\"]*)\" and code (\\d+)$")
    public void ensureTheServiceAddedWithUrlMessageAndCodeCode(String url, String message, Integer code) throws Throwable {
        waitText(driver, url);
        waitText(driver, message);
        waitText(driver, code.toString());
    }
}
