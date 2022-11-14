package tss.sumdu.test.unit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tss.sumdu.util.Service;
import tss.sumdu.util.ServiceHolder;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.*;

public class ServiceHolderTest {

    Service service = null;
    ServiceHolder holder = null;

    private Service generateService(String url, Integer code, String message) {
        Service service = mock(Service.class);
        when(service.getUrl()).thenReturn(url);
        when(service.getCode()).thenReturn(code);
        when(service.getMessage()).thenReturn(message);
        return service;
    }

    @BeforeMethod
    public void createService() {
        service = generateService("/test", 200, "text");
    }

    @BeforeMethod
    public void initHolder() {
        holder = new ServiceHolder();
    }

    @Test
    public void testInsertOne() {
        assertEquals(0, holder.size());
        holder.putVal(service);
        assertEquals(1, holder.size());
        assertEquals(holder.fetchVal(service.getUrl()), service);
        assertNull(holder.fetchVal("no-one"));
    }

    @Test
    void testReplaceOld() {
        assertEquals(0, holder.size());
        holder.putVal(service);
        assertEquals(1, holder.size());
        holder.putVal(service);
        assertEquals(1, holder.size());
    }

    @Test
    public void testRemoveExtraCapacity() {
        holder.putVal(service);
        for (int i = 0; i < ServiceHolder.MAX_CAPACITY + 5; i += 1) {
            holder.putVal(generateService("/test_" + i, 404, "text"));
        }
        assertEquals(ServiceHolder.MAX_CAPACITY, holder.size());
        assertNull(holder.fetchVal(service.getUrl()));
    }

    @Test
    public void testIgnoreSlashes() {
        String variant1 = "/test";
        String variant2 = "test";
        String variant3 = "test/";
        Service service1 = generateService(variant1, 404, null);
        Service service2 = generateService(variant2, 403, null);
        Service service3 = generateService(variant3, 405, null);
        holder.putVal(service1);
        holder.putVal(service2);
        holder.putVal(service3);
        assertEquals(1, holder.size());
        assertNotNull(holder.fetchVal(variant1));
        assertNotNull(holder.fetchVal(variant2));
        assertNotNull(holder.fetchVal(variant3));
    }

}
