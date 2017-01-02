package com.tgzhao.clannad.common.guavautil;

import com.apple.laf.AquaImageFactory;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import jdk.internal.jfr.events.FileWriteEvent;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.jar.Attributes;

/**
 * Created by tgzhao on 16/12/7.
 */
public class ObjectsMain {


    public static void main(String[] args) {
        String str = "aaa in hello world.";
        ArrayList<Student> students2 = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("helo", 34, 5));
        String aa = "adfa";

        SoftReference<String>[] people =
                new SoftReference[100000];
        WeakReference<String> wr = new WeakReference<String>(str);
    }
}

class Student implements Comparable<Student>{
    public String name;
    public Integer age;
    public Integer score;

    Student(String name, int age,int score) {
        this.name = name;
        this.age = age;
        this.score=score;
    }

    @Override
    public int compareTo(Student other) {
        return ComparisonChain.start()
                .compare(name, other.name)
                .compare(age, other.age)
                .compare(score, other.score, Ordering.natural().nullsLast())
                .result();
    }
}

class Employee2 implements Comparable {
    private String name;
    private Integer age;
    private String job;

    // methods ...

    public int compareTo(Employee2 other) {
        return ComparisonChain.start()
                // 以英文字母(从a到z)的自然顺序，NULL值放在最后
                .compare(this.name, other.name, Ordering.natural().nullsLast())
                // 以数字的反序(从大到小)，NULL值处于最后
                .compare(this.age, other.age, Ordering.natural().reverse().nullsLast())
                .compare(this.job, other.job, Ordering.natural().nullsLast())
                .result();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, age, job);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("name", name)
                .add("age", age)
                .add("job", job)
                .toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}