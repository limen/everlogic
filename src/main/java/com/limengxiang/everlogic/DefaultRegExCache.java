package com.limengxiang.everlogic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class DefaultRegExCache implements IRegExCache {

    private Map<String, Pattern> cachePool;

    private int capacity;

    public DefaultRegExCache() {
        cachePool = new HashMap<>();
        capacity = 1000;
    }

    public void setCapacity(int cap) {
        capacity = cap;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public Pattern compile(String pattern) {
        if (cachePool.containsKey(pattern)) {
            return cachePool.get(pattern);
        }
        Pattern p = Pattern.compile(pattern);
        if (cachePool.size() < capacity) {
            cachePool.putIfAbsent(pattern, p);
        }
        return p;
    }
}
