package tss.sumdu.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Set;

public class ServiceHolder {
    public static final int MAX_CAPACITY = 20;
    private final Cache<String, Service> cache;

    public ServiceHolder() {
        cache = CacheBuilder.newBuilder().maximumSize(MAX_CAPACITY).concurrencyLevel(1).build();
    }

    public Service fetchVal(String key) {
        return cache.getIfPresent(Helpers.normalizeURL(key));
    }

    public void putVal(Service val) {
        String key = Helpers.normalizeURL(val.getUrl());
        cache.put(key, val);
    }

    public long size() {
        return cache.size();
    }


    @SuppressWarnings({"rawtypes", "unused"})
    public Set entrySet() {
        return cache.asMap().entrySet();
    }

}