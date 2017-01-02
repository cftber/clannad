package com.tgzhao.clannad.common.guavautil;

import com.google.common.base.*;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by tgzhao on 16/12/19.
 */
public class StringUtil {

    public static void main(String[] args) {
        Ints.compare(4, 2);

        CharMatcher matcher = CharMatcher.inRange('a', 'd').or(CharMatcher.inRange('A', 'D'));
        System.out.println(matcher.removeFrom("hello world inabcdefghikkkk"));
        System.out.println(matcher.collapseFrom("hello world inabcedfghikkk", '*'));
        Join();
        spilt();
    }

    public static void PredicateFunction() {
        ArrayList<String> strArr = Lists.newArrayList(
                " test1","test2 "," test3 ",null,"test4",null,null,"", "  ");
        Predicate<String> EMPTY_OR_NULL_FILTER = new Predicate<String>() {
            @Override
            public boolean apply(String str){
                str = Strings.nullToEmpty(str).trim();
                return !Strings.isNullOrEmpty(str);
            }
        };

        Function<String, String> TRIM_RESULT = new Function<String, String>(){
            @Override
            public String apply(String str){
                return Strings.nullToEmpty(str).trim();
            }
        };

        String joinStr = Joiner.on(';')
                .skipNulls()
                .join(Collections2.transform(Collections2.filter(strArr, EMPTY_OR_NULL_FILTER), TRIM_RESULT));

    }
    public static void Join() {
        Joiner joiner = Joiner.on('.').useForNull("999");
        System.out.println(joiner.join("hello", new Object(), null, "liuliuliu"));
        System.out.println(Joiner.on(',').join(Arrays.asList(1,2,3,4)));
    }

    public static void spilt() {
        Iterable<String> result = Splitter.on(',')
                .omitEmptyStrings()
                .trimResults()
                .split("foo, bar,,,   hello world");
        System.out.println(result);

        //Splitter.on(CharMatcher.JAVA_ISO_CONTROL)
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom("uue333883u99ej", "*"); //用*号替换所有数字
        System.out.println(noDigits);

    }
}
