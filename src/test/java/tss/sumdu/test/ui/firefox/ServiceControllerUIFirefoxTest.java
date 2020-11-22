package tss.sumdu.test.ui.firefox;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import tss.sumdu.test.ui.common.ServiceControllerUIFunctionalTest;

import static io.github.bonigarcia.wdm.DriverManagerType.FIREFOX;

public class ServiceControllerUIFirefoxTest extends ServiceControllerUIFunctionalTest {

    @BeforeAll
    static void initDriver() {
        WebDriverManager.getInstance(FIREFOX).setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(inDebugMode());
        driver = new FirefoxDriver(options);
    }

}
