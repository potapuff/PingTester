package tss.sumdu.test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumHelper {
    public static WebElement elementByName(WebDriver driver, String name) {
        try {
            WebElement element = driver.findElement(By.name(name));
            if (element != null) {
                return element;
            }
        } catch (Exception ex) {
            assertEquals("Element <" + name + ">", ex.getMessage());
        }
        assertEquals("Element <" + name + ">", "Not found ");
        return null;
    }

    public static WebElement buttonByName(WebDriver driver, String name) {
        try {
            WebElement element = driver.findElement(By.xpath("//button[text()=\"" + name + "\"]"));
            if (element != null) {
                return element;
            }
        } catch (Exception ex) {
            assertEquals("Button <" + name + ">", ex.getMessage());
        }
        assertEquals("Button <" + name + ">", "Not found ");
        return null;
    }

    public static void waitText(WebDriver driver, String text) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        ExpectedCondition<Boolean> expectation = null;
        try {
            expectation = browser -> (Objects.requireNonNull(browser).getPageSource().contains(text));
        } catch (NullPointerException ex) {
            assertEquals("Element found", "Code throw error");
        }
        wait.until(expectation);
    }

    public static WebDriver chromeDriver(boolean debugMode) {
        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(!debugMode);
        options.addArguments("--disable-gpu");
        return new ChromeDriver(options);
    }

    public static WebDriver firefoxDriver(boolean debugMode) {
        WebDriverManager.getInstance(FIREFOX).setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(debugMode);
        return new FirefoxDriver(options);
    }
}
