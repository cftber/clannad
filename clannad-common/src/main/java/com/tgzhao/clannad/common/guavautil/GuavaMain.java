package com.tgzhao.clannad.common.guavautil;


import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * Created by tgzhao on 16/12/7.
 */
public class GuavaMain {

    public static void main(String[] args) throws Exception {
        String name="2323";
        String ddd = Preconditions.checkNotNull(name, "neme为null");
        System.out.println(ddd);
        testOptional2();
    }
    public static void testOptional() throws Exception {
        Optional<Integer> possible= Optional.of(6);
        if(possible.isPresent()){
            System.out.println("possible isPresent:"+possible.isPresent());
            System.out.println("possible value:"+possible.get());
        }
    }
    public static void testOptional2() throws Exception {
        Optional<Integer> possible=Optional.of(6);
        Optional<Integer> absentOpt=Optional.absent();
        Optional<Integer> NullableOpt=Optional.fromNullable(null);
        Optional<Integer> NoNullableOpt=Optional.fromNullable(10);
        if(possible.isPresent()){
            System.out.println("possible isPresent:"+possible.isPresent());
            System.out.println("possible value:"+possible.get());
        }
        if(absentOpt.isPresent()){
            System.out.println("absentOpt isPresent:"+absentOpt.isPresent()); ;
        }
        if(NullableOpt.isPresent()){
            System.out.println("fromNullableOpt isPresent:"+NullableOpt.isPresent()); ;
        }
        if(NoNullableOpt.isPresent()){
            System.out.println("NoNullableOpt isPresent:"+NoNullableOpt.get()); ;
        }
    }
    public static void testNull(){
        int age=0;
        System.out.println("user age:"+age);

        long money;
        money=10L;
        System.out.println("user money"+money);

        String name=null;
        System.out.println("user name:"+name);
    }
    public static void testNullObject() {
        if (null instanceof java.lang.Object) {
            System.out.println("null属于java.lang.Object类型");
        } else {
            System.out.println("null不属于java.lang.Object类型");
        }
    }
}
