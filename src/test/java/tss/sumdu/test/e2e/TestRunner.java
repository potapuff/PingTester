package tss.sumdu.test.e2e;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/tss/sumdu/test/e2e"},
        glue = {"tss.sumdu.test.e2e.steps"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

@Override
@DataProvider(parallel = false)
public Object[][] scenarios() {
    return super.scenarios();
}
}