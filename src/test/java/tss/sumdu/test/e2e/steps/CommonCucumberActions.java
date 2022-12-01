package tss.sumdu.test.e2e.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import tss.sumdu.UrlTesterApp;

import java.text.SimpleDateFormat;

import static com.codeborne.selenide.Selenide.screenshot;

abstract class CommonCucumberActions {
static UrlTesterApp app = null;
int step = 0;
Scenario scenario;

@Before
public void configureSelenide() {
    Configuration.browser = "chrome";
    Configuration.baseUrl = UrlTesterApp.URL;
    Configuration.holdBrowserOpen = true;
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    Configuration.reportsFolder = "test-result/" + timeStamp;
}

@Before
public void before(Scenario scenario) {
    this.scenario = scenario;
}

@Before
public void startServer() {
    app = new UrlTesterApp();
    app.start(UrlTesterApp.PORT);
}

@After
public void stopServer() {
    app.stop();
    app = null;
}

@After
public void finish() {
    if (app != null) {
        app.stop();
        app = null;
    }
}

@AfterStep
public void afterStep() {
    screenshot(scenario.getName() + "-" + String.valueOf(step++));
}
}
