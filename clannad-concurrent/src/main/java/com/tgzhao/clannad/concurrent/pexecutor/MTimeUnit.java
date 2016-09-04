package com.tgzhao.clannad.concurrent.pexecutor;

import java.util.concurrent.TimeUnit;

/**
 * Created by tgzhao on 16/9/3.
 */
public class MTimeUnit {

    public static void main(String[] args) throws InterruptedException {

        TimeUnitDemo.main(null);

        System.out.println(TimeUnit.DAYS.toHours(2)); //48
        System.out.println(TimeUnit.SECONDS.toMillis(44));


        System.out.println(TimeUnit.SECONDS.toMinutes(120));
        System.out.println(TimeUnit.SECONDS.toMinutes(70));
        System.out.println(TimeUnit.SECONDS.toHours(70));
        TimeUnit.SECONDS.sleep(5);
        System.out.println("after sleep");
    }
}

class TimeUnitDemo {
    private TimeUnit timeUnit = TimeUnit.DAYS;

    public static void main(String[] args) {
        TimeUnitDemo demo = new TimeUnitDemo();
        demo.outInfo();
        demo.timeUnit = TimeUnit.HOURS;
        demo.outInfo();
        demo.timeUnit = TimeUnit.MINUTES;
        demo.outInfo();
        demo.timeUnit = TimeUnit.SECONDS;
        demo.outInfo();
    }

    public void outInfo() {
        System.out.println(timeUnit.name());
        System.out.println(timeUnit.toDays(1));
        System.out.println(timeUnit.toHours(1));
        System.out.println(timeUnit.toMinutes(1));
        System.out.println(timeUnit.toMicros(1));
        System.out.println(timeUnit.toMillis(1));
        System.out.println(timeUnit.toNanos(1));
        System.out.println(timeUnit.toSeconds(1));
        System.out.println("1天有" + (timeUnit.convert(1, TimeUnit.DAYS)) + timeUnit.name());
        System.out.println("12小时" + (timeUnit.convert(12, TimeUnit.HOURS)) + timeUnit.name());
        System.out.println("3600秒有" + (timeUnit.convert(36000, TimeUnit.MINUTES)) + timeUnit.name());
        System.out.println("-------------------");
    }
}