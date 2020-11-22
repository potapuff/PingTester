package tss.sumdu.test.integration;

import io.javalin.http.Context;
import org.junit.jupiter.api.Test;
import tss.sumdu.controllers.ServiceController;
import tss.sumdu.test.utils.TestHelper;

import static org.mockito.Mockito.*;

public class ServiceControllerFunctionalTest {

    final Context ctx = mock(Context.class);

    @Test
    public void testCreateValidService() {
        String newService = "/test/test";
        String body = TestHelper.jsonGenerator(newService, "200", "test");
        when(ctx.body()).thenReturn(body);
        ServiceController.createOrUpdate(ctx);
        verify(ctx).redirect(newService);
    }

    @Test
    public void testCreateMalformedJson() {
        String body = TestHelper.jsonGenerator("/test/test", "400", "test").replace('{', '[');
        when(ctx.body()).thenReturn(body);
        ServiceController.createOrUpdate(ctx);
        verify(ctx).status(400);
    }

}
