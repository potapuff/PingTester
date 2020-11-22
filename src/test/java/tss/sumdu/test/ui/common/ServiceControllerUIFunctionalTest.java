package tss.sumdu.test.ui.common;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import tss.sumdu.UrlTesterApp;
import tss.sumdu.test.utils.TestHelper;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public abstract class ServiceControllerUIFunctionalTest extends CommonUITest {

    @Test
    public void testCreateValidService() {
        String defaultURL = "/test/test";
        driver.get(UrlTesterApp.URL + '/');
        driver.findElement(By.id("submit")).click();
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        ExpectedCondition<Boolean> expectation = null;
        try {
            expectation = driver -> (Objects.requireNonNull(driver).getPageSource().contains(defaultURL));
        } catch (NullPointerException ex) {
            assertEquals("Element found", "Code throw error");
        }
        wait.until(expectation);

    }

    @Test
    public void testCreateMalformedJson() {
        String body = TestHelper.jsonGenerator("/test/test", "400", "test").replace('{', '[');
        driver.get(UrlTesterApp.URL + '/');

        WebElement textarea = driver.findElement(By.id("request"));
        textarea.clear();
        textarea.sendKeys(body);
        driver.findElement(By.id("submit")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement info = driver.findElement(By.id("info"));
        assertTrue(info.getAttribute("innerText").contains("400"));
    }

}
