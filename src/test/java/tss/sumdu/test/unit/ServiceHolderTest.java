package tss.sumdu.test.unit;

import org.junit.jupiter.api.Test;
import tss.sumdu.util.Service;

import org.junit.jupiter.api.BeforeEach;
import tss.sumdu.util.ServiceHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceHolderTest {

    Service service = null;
    ServiceHolder holder = null;

    private Service generateService(String url, Integer code, String message){
        Service service = mock(Service.class);
        when(service.getUrl()).thenReturn(url);
        when(service.getCode()).thenReturn(code);
        when(service.getMessage()).thenReturn(message);
        return service;
    }

    @BeforeEach
    public void createService(){
        service = generateService("/test", 200, "text");
    }

    @BeforeEach
    public void initHolder(){
        holder = new ServiceHolder();
    }

    @Test
    public void testInsertOne(){
        assertEquals(holder.size(), 0);
        holder.putVal(service);
        assertEquals(holder.size(), 1);
        assertEquals(holder.fetchVal(service.getUrl()), service);
        assertNull(holder.fetchVal("no-one"));
    }

    @Test void testReplaceOld(){
        assertEquals(holder.size(), 0);
        holder.putVal(service);
        assertEquals(holder.size(), 1);
        holder.putVal(service);
        assertEquals(holder.size(), 1);
    }

    @Test
    public void testRemoveExtraCapacity(){
        holder.putVal(service);
        for (int i=0; i< ServiceHolder.MAX_CAPACITY+5; i+=1){
            holder.putVal(generateService("/test_"+i, 404, "text"));
        }
        assertEquals(holder.size(), ServiceHolder.MAX_CAPACITY);
        assertNull(holder.fetchVal(service.getUrl()));
    }

    @Test
    public void testIgnoreCaseSearch(){

    }

    @Test
    public void testIgnoreSlashes(){
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
