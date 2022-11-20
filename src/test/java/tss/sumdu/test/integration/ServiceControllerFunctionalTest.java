package tss.sumdu.test.integration;

import io.javalin.http.Context;
import org.testng.annotations.Test;
import tss.sumdu.controllers.ServiceController;
import tss.sumdu.test.utils.TestHelper;

import static org.mockito.Mockito.*;

public class ServiceControllerFunctionalTest {

    final Context ctx = mock(Context.class);

    @Test
    public void testCreateValidService() {
        String newServiceUri = "/test/test";
        String body = TestHelper.jsonGenerator(newServiceUri, "200", "test");
        when(ctx.body()).thenReturn(body);
        ServiceController.createOrUpdate(ctx);
        verify(ctx).redirect(newServiceUri);
    }

    @Test
    public void testCreateMalformedJson() {
        String body = TestHelper.jsonGenerator("/test/test", "400", "test").replace('{', '[');
        when(ctx.body()).thenReturn(body);
        ServiceController.createOrUpdate(ctx);
        verify(ctx).status(400);
    }
}
