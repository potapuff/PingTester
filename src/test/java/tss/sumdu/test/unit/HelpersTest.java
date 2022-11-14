package tss.sumdu.test.unit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tss.sumdu.util.Helpers;

import static org.testng.AssertJUnit.assertEquals;

public class HelpersTest {

    @DataProvider(name = "testUrlNormalization")
    public static Object[][] urlForNormalization() {
        String url = "/test/tests";
        return new Object[][]
                {{url, "/test/tests"},
                        {url, "/test/tests/"},
                        {url, "test/tests"},
                        {url, "test/tests/"}};
    }

    @Test(dataProvider = "testUrlNormalization")
    public void testUrlNormalization(String expected, String ourTry) {
        assertEquals(expected, Helpers.normalizeURL(ourTry));
    }

}
