package com.tgzhao.clannad.concurrent.collec;

import java.util.*;

/**
 * Created by tgzhao on 16/9/13.
 */
public class App {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(5);
        list.add(2);
        list.add(1);
        list.add(9);
        list.add(0);

        System.out.println("原序列:");
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }
        System.out.println();

        /*根据步长进行循环*/
        Collections.rotate(list, 1);
        System.out.println("循环后:");
        for (Integer integer : list) {
            System.out.print(integer+" ");
        }

        /*
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);

        for (Integer integer : list) {
            System.out.println(integer);
        }
        *//*找出最大值*//*
        int max = Collections.max(list);
        System.out.println("最大的为:"+max);

        *//*用指定元素替换指定list中的元素*//*
        Collections.fill(list, 6);
        System.out.println("替换后:");
        for (Integer integer : list) {
            System.out.println(integer);
        }

        *//*找出某个list里某个元素的个数*//*
        int count = Collections.frequency(list, 6);
        System.out.println("里面有6的个数:"+count);*/
       /* List<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        iterator.next();

        for(Iterator it = list.iterator(); it.hasNext();) {

        }

        List<Object> syncObjList = Collections.synchronizedList(new ArrayList<Object>());*/
    }
}
