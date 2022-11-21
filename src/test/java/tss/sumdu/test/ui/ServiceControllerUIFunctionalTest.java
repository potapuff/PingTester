package tss.sumdu.test.ui;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import tss.sumdu.test.utils.TestHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ServiceControllerUIFunctionalTest extends CommonUITest {

@Test
public void testCreateValidService() {
    String testName = "testCreateValidService";
    int step = 0;
    String defaultURL = "/test/test" + Double.toString(Math.random());
    open("/");
    screenshot(testName + (step++));
    SelenideElement element = $(By.name("url"));
    element.clear();
    element.sendKeys(defaultURL);
    screenshot(testName + (step++));
    $("#submit").click();
    $("body").shouldHave(text(defaultURL));
    screenshot(testName + (step++));
}

@Test
public void testCreateMalformedJson() {
    String testName = "testCreateMalformedJson";
    int step = 0;
    String body = TestHelper.jsonGenerator("/test/test", "400", "test").replace('{', '[');
    open("/");
    screenshot(testName + (step++));
    SelenideElement element = $("#request");
    element.clear();
    element.sendKeys(body);
    screenshot(testName + (step++));
    $("#submit").click();
    $("#info").shouldHave(text("400"));
    screenshot(testName + (step++));
}

}
