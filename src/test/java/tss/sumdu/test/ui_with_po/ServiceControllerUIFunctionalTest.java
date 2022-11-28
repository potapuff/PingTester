package tss.sumdu.test.ui_with_po;

import org.testng.annotations.Test;
import tss.sumdu.test.ui.CommonUITest;
import tss.sumdu.test.ui_with_po.page_object.IndexPage;
import tss.sumdu.test.utils.TestHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class ServiceControllerUIFunctionalTest extends CommonUITest {

@Test
public void testCreateValidService() {
    String testName = "testCreateValidService";
    int step = 0;
    String defaultURL = "/test/test" + Math.random();
    IndexPage page = open("/", IndexPage.class);
    screenshot(testName + (step++));
    page.setUrl(defaultURL);
    screenshot(testName + (step++));
    page.send();
    page.body.shouldHave(text(defaultURL));
    screenshot(testName + (step++));
}

@Test
public void testCreateMalformedJson() {
    String testName = "testCreateMalformedJson";
    int step = 0;
    String defaultURL = "/test/test" + Math.random();
    String body = TestHelper.jsonGenerator(defaultURL, "400", "test").replace('{', '[');
    IndexPage page = open("/", IndexPage.class);
    screenshot(testName + (step++));
    page.setRequest(body);
    screenshot(testName + (step++));
    page.send();
    page.info.shouldHave(text("400"));
    page.info.shouldHave(text(defaultURL));
    screenshot(testName + (step++));
}

}
