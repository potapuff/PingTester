package tss.sumdu.test.e2e.steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import tss.sumdu.UrlTesterApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ServiceController extends CommonCucumberActions {

@Given("^the user is on ([\\S]*) page$")
public void goToPage(String url) throws Throwable {
    open(UrlTesterApp.URL + url);
}

@When("^he enters \"([^\"]*)\" as (\\w*)$")
public void heEntersAsUrl(String text, String name) throws Throwable {
    SelenideElement element = $(By.name(name));
    element.clear();
    element.sendKeys(text);
}

@And("^he click on \"([^\"]*)\" button$")
public void heClickOnButton(String title) throws Throwable {
    $(By.xpath("//button[text()='" + title + "']")).click();
}

@Then("^ensure the service added with url \"([^\"]*)\", message \"([^\"]*)\" and code (\\d+)$")
public void ensureTheServiceAddedWithUrlMessageAndCodeCode(String url, String message, Integer code) throws Throwable {
    SelenideElement body = $("body");
    body.shouldHave(text(url));
    body.shouldHave(text(message));
    body.shouldHave(text(code.toString()));
}

}
