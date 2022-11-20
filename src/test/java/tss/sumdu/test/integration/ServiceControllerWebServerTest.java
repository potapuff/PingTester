package tss.sumdu.test.integration;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tss.sumdu.UrlTesterApp;
import tss.sumdu.test.utils.TestHelper;
import tss.sumdu.util.CSRFTokenService;

import static org.testng.Assert.assertEquals;

@SuppressWarnings("rawtypes")
public class ServiceControllerWebServerTest {

    private static UrlTesterApp app = null;

    @BeforeTest
    static void initServer() {
        app = new UrlTesterApp();
        app.start(UrlTesterApp.PORT);
    }

    @AfterTest
    static void stopServer() {
        app.stop();
        app = null;
    }

    @Test
    public void testCreateValidService() {
        String body = TestHelper.jsonGenerator("/test/test", "218", "test");
        HttpResponse response = Unirest.post(UrlTesterApp.URL + "/")
                .header("Content-Type", "application/json")
                .header("X-CSRF-TOKEN", CSRFTokenService.generateToken(CSRFTokenService.NO_AUTH))
                .body(body)
                .asEmpty();
        assertEquals(response.getStatus(), 218);
    }

    @Test
    public void testCreateMalformedJson() {
        String body = TestHelper.jsonGenerator("/test/test", "400", "test").replace('{', '[');
        HttpResponse response = Unirest.post(UrlTesterApp.URL + "/")
                .header("Content-Type", "application/json")
                .header("X-CSRF-TOKEN", CSRFTokenService.generateToken(CSRFTokenService.NO_AUTH))
                .body(body).asEmpty();
        assertEquals(response.getStatus(), 400);
    }
}
