package tss.sumdu.test.e2e.steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.uk.Дано;
import io.cucumber.java.uk.Коли;
import io.cucumber.java.uk.Та;
import io.cucumber.java.uk.Тоді;
import org.openqa.selenium.By;
import tss.sumdu.UrlTesterApp;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ServiceController extends CommonCucumberActions {

@Дано("^користувач знаходиться на сторінці ([\\S]*)$")
public void goToPage(String url) throws Throwable {
    open(UrlTesterApp.URL + url);
}

@Коли("^він вводить \"([^\"]*)\" в поле (#?\\w*)$")
public void heEntersAsUrl(String text, String name) throws Throwable {
    SelenideElement element = name.startsWith("#") ? $(name) : $(By.name(name));
    element.clear();
    element.sendKeys(text);
}

@Та("^натискає на кнопку \"([^\"]*)\"$")
public void heClickOnButton(String title) throws Throwable {
    $(By.xpath("//button[text()='" + title + "']")).click();
}

@Тоді("^перевір, що створено мокап із адресою \"([^\"]*)\", повідомленням \"([^\"]*)\" та кодом (\\d+)$")
public void ensureTheServiceAddedWithUrlMessageAndCodeCode(String url, String message, Integer code) throws Throwable {
    SelenideElement body = $("body");
    body.shouldHave(text(url));
    body.shouldHave(text(message));
    body.shouldHave(text(code.toString()));
}

@Тоді("^перевір що зявивився текст \"([^\"]*)\"$")
public void checkErrorMessage(String message) {
    $("#info").shouldHave(text(message));
}
}
