package com.tgzhao.clannad.common.guavautil;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created by tgzhao on 16/12/28.
 */
public class GuavaMap {

    public static void main(String[] args) {
        mapConvert();
    }

    public static void mapConvert() {
        BiMap<String, String> map = HashBiMap.create();
        map.put("AAA", "BBB");
        map.put("aaa", "BBB1");
        System.out.println(map);
        BiMap<String, String> mapC = map.inverse();
        System.out.println(mapC);
    }
}
