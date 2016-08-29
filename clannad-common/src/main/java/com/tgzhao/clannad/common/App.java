package com.tgzhao.clannad.common;

import com.tgzhao.clannad.common.util.DateCache;
import com.tgzhao.clannad.common.util.HashUtil;

/**
 * Created by tgzhao on 2016/8/26.
 */
public class App {

    public static void main(String[] args) {

//        System.out.println(HashUtil.hash("012016081901000000000000803452"));
        dateCacheTest();
    }

    static void dateCacheTest() {
        long begin = System.currentTimeMillis();
        int i = 1000*10000;
        while (i > 0) {
            long tmp = DateCache.currTimeMillis();
//            long tmp = System.currentTimeMillis();
            if (tmp > 0) {
                i--;
            }

        }
        long end = System.currentTimeMillis();

        System.out.println("time span:" + (end - begin) + "ms");
    }
}
