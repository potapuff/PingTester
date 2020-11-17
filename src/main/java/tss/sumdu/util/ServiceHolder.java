package tss.sumdu.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Set;

public class ServiceHolder
{
    private final Cache<String, Service> cache;
    public static int MAX_CAPACITY = 20;

    public ServiceHolder()
    {
        cache = CacheBuilder.newBuilder().maximumSize(MAX_CAPACITY).concurrencyLevel(1).build();
    }

    public Service fetchVal(String key)
    {
        if (key.charAt(0) != '/') {
           key = "/" + key;
        }
        key = key.replaceFirst("/+$", "");

        return cache.getIfPresent(key);
    }

    public void putVal(Service val)
    {
        cache.put(val.getUrl(), val);
    }

    public long size(){
        return cache.size();
    }

    @SuppressWarnings("unused")
    public Set entrySet(){
        return cache.asMap().entrySet();
    }

}