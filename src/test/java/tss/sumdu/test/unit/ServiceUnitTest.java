package tss.sumdu.test.unit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tss.sumdu.util.Service;
import tss.sumdu.util.ValidationError;

import static org.junit.jupiter.api.Assertions.*;

class ServiceUnitTest {

    @Test
    public void testCreateNormalService() {
        String url = "/test";
        Integer code = 400;
        String message = "test message";
        Service service = new Service(url, code, message);
        assertEquals(url, service.getUrl());
        assertEquals(code, service.getCode());
        assertEquals(message, service.getMessage());
    }

    @Disabled
    @Test
    public void testCreateServiceWithMalformedUrl() {
        String url = "/test";
        Integer code = 400;
        String message = "test message";
        ValidationError exception = null;
        try {
            new Service(url, code, message);
        } catch (ValidationError ex) {
            exception = ex;
        }
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(url));
    }

    @Test
    public void testCreateServiceWithGoodCode() {
        int[] goodCodes = {100, 200, 400, 500};
        String url = "/test";
        String message = "message";

        for (Integer code : goodCodes) {
            Service service = new Service(url, code, message);
            assertEquals(service.getCode(), code);
        }
    }

    @Test
    public void testCreateServiceWithBadCode() {
        int[] badCodes = {-1, 1, 99, 1000, 1001};
        String url = "/test";
        String message = "message";

        ValidationError exception = null;
        for (Integer code : badCodes) {
            try {
                new Service(url, code, message);
            } catch (ValidationError e) {
                exception = e;
            }
            assertNotNull(exception);
            assertTrue(exception.getMessage().contains(code.toString()));
        }
    }


}

