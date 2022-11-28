package tss.sumdu.test.ui_with_po;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import tss.sumdu.UrlTesterApp;

import java.text.SimpleDateFormat;

public abstract class CommonUITest {

public static UrlTesterApp app = null;
static private boolean debugMode = false;

@SuppressWarnings("unused")
public static void enableDebugMode() {
    debugMode = true;
}

public static boolean inDebugMode() {
    return !debugMode;
}

@BeforeTest
static void configureSelenide() {
    enableDebugMode();
    Configuration.browser = "chrome";
    Configuration.baseUrl = UrlTesterApp.URL;
    Configuration.holdBrowserOpen = !inDebugMode();
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    Configuration.reportsFolder = "test-result/" + timeStamp;
}

@BeforeTest
static void startServer() {
    app = new UrlTesterApp();
    app.start(UrlTesterApp.PORT);
}

@AfterTest
static void stopServer() {
    app.stop();
    app = null;
}

}
