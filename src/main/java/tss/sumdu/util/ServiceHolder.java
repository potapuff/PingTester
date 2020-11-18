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
        return cache.getIfPresent(Helpers.normalizeURL(key));
    }

    public void putVal(Service val)
    {
        String key = Helpers.normalizeURL(val.getUrl());
        cache.put(key, val);
    }

    public long size(){
        return cache.size();
    }

    @SuppressWarnings("unused")
    public Set entrySet(){
        return cache.asMap().entrySet();
    }

}