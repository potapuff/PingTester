package tss.sumdu.test.ui.firefox;

import org.junit.jupiter.api.BeforeAll;
import tss.sumdu.test.ui.common.ServiceControllerUIFunctionalTest;
import tss.sumdu.test.utils.SeleniumHelper;

public class ServiceControllerUIFirefoxTest extends ServiceControllerUIFunctionalTest {

    @BeforeAll
    static void initDriver() {
        driver = SeleniumHelper.firefoxDriver(inDebugMode());
    }

}
