package com.tgzhao.clannad.common.guavautil;

/**
 * Created by tgzhao on 17/1/1.
 */
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import static com.google.common.base.Preconditions.*;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Employee implements Comparable<Employee> {
    private String name;
    private Integer age;
    private Job job;

    public Employee() { }

    public Employee (String name,int age) {
        this.name = checkNotNull(name, "name 不能为null:", age);
        checkArgument(age > 20, "age 必须大于 20:",age);
        this.age = age;
    }

    public String getCoordinatesAsText() {
//		return (getGpsCoordinates() != null) ?  getGpsCoordinates() : "DEFAULT_LOCATION";
        return MoreObjects.firstNonNull(getGpsCoordinates(), "DEFAULT_LOCATION");
    }

    private String getGpsCoordinates() {
        // retrieve GPS coordinates from satellite // VERY SLOW!!
        return null;
    }

    public int compareTo(Employee other) {
        return ComparisonChain
                .start()
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
    public String toString () {
        return MoreObjects.toStringHelper(this)
                //.omitNullValues()
                .add("name", name)
                .add("age", age)
                .add("job", job)
                .toString();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Job getJob() {
        return job;
    }
    public void setJob(Job job) {
        this.job = job;
    }
}
