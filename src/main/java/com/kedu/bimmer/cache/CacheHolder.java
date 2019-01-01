package com.kedu.bimmer.cache;

import com.kedu.bimmer.model.Article;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jef
 */
public class CacheHolder {

    private static final Map<String, Article> map = new HashMap<>();

    public static void put(String key, Article value) {
        map.put(key, value);
    }

    public static Article get(String key) {
        return map.get(key);
    }

    public static void remove(String key) {
        map.remove(key);
    }
}
