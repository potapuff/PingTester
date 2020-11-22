package tss.sumdu.test.unit;

import org.junit.jupiter.api.Test;
import tss.sumdu.util.Helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpersTest {

    @Test
    public void testUrlNormalization(){
        String url = "/test/tests";
        assertEquals(url, Helpers.normalizeURL("/test/tests"));
        assertEquals(url, Helpers.normalizeURL("/test/tests/"));
        assertEquals(url, Helpers.normalizeURL( "test/tests"));
        assertEquals(url, Helpers.normalizeURL( "test/tests/"));
    }

}
