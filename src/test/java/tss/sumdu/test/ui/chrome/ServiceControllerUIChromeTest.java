package tss.sumdu.test.ui.chrome;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import tss.sumdu.test.ui.common.ServiceControllerUIFunctionalTest;

import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;

public class ServiceControllerUIChromeTest extends ServiceControllerUIFunctionalTest {

    @BeforeAll
    static void initDriver() {
        WebDriverManager.getInstance(CHROME).setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(inDebugMode());
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }

}
