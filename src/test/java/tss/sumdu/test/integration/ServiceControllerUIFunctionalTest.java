package tss.sumdu.test.integration;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tss.sumdu.UrlTesterApp;
import tss.sumdu.test.utils.TestHelper;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ServiceControllerUIFunctionalTest {

    //Debug mode
    static final boolean DEBUG = true;
    private static final UrlTesterApp app = new UrlTesterApp();
    private static WebDriver driver;

    @BeforeAll
    static void initServer(){
        app.start(UrlTesterApp.PORT);
        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions options = new ChromeOptions();
        if (! DEBUG) {
            options.addArguments("--headless");
        }
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testCreateValidService() {
        String defaultURL = "/test/test";
        driver.get(UrlTesterApp.URL+'/');
        driver.findElement(By.id("submit")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(driver.getPageSource().contains(defaultURL));
    }

    @Test
    public void testCreateMalformedJson() {
        String body = TestHelper.jsonGenerator("/test/test", "400", "test").replace('{','[');
        driver.get(UrlTesterApp.URL+'/');

        WebElement textarea = driver.findElement(By.id("request"));
        textarea.clear();
        textarea.sendKeys(body);
        driver.findElement(By.id("submit")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement info = driver.findElement(By.id("info"));
        assertTrue(info.getAttribute("innerText").contains("400"));
    }

    @AfterAll
    static void  stopServer(){
        app.stop();
        if (!DEBUG) {
            driver.quit();
        }
    }

}