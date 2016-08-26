package com.tgzhao.clannad.common.util;

/**
 * Created by tgzhao on 2016/8/26.
 */
public class HashUtil {

    /**
     * 改进的32位FNV算法1
     *
     * @param data
     *            字符串
     * @return int值
     */
    public static int hash(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        hash = Math.abs(hash);
        return hash;
    }
}
