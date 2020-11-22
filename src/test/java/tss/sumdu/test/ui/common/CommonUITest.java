package tss.sumdu.test.ui.common;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import tss.sumdu.UrlTesterApp;

public abstract class CommonUITest {

    public static UrlTesterApp app = null;
    public static WebDriver driver;
    // Debug mode (don't close browser after test finish)
    static private boolean debugMode = false;

    @SuppressWarnings("unused")
    public static void enableDebugMode() {
        debugMode = true;
    }

    public static boolean inDebugMode() {
        return !debugMode;
    }

    @BeforeAll
    static void startServer() {
        System.out.println("Starting!");
        app = new UrlTesterApp();
        app.start(UrlTesterApp.PORT);
    }

    @BeforeAll
    static void initDriver() {
        throw new NotImplementedException("Must be defined in child-classes");
    }

    @AfterAll
    static void stopServer() {
        app.stop();
        app = null;
    }

    @AfterAll
    static void stopDriver() {
        if (inDebugMode()) {
            driver.quit();
        }
    }

}
