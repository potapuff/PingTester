package tss.sumdu.test.ui.chrome;

import org.junit.jupiter.api.BeforeAll;
import tss.sumdu.test.ui.common.ServiceControllerUIFunctionalTest;
import tss.sumdu.test.utils.SeleniumHelper;


public class ServiceControllerUIChromeTest extends ServiceControllerUIFunctionalTest {

    @BeforeAll
    static void initDriver() {
        driver = SeleniumHelper.chromeDriver(inDebugMode());
    }

}
