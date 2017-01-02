package com.tgzhao.clannad.common.guavautil;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tgzhao on 17/1/1.
 */
public class EmployeeDemo {

    public static void main(String[] args) {
        List<Employee> employees = Lists.newArrayList();
        Employee employee1 = new Employee("aaa", 21);
        employee1.setJob(new Job("jobname"));
        Employee employee2 = new Employee("bbbb", 35);
        employee2.setJob(new Job("bbbjob"));

        employees.add(new Employee("aaa", 21));
        employees.add(new Employee("bbbb", 34));
        employees.add(new Employee("aaa", 43));
        employees.add(new Employee("bbbb", 32));
        employees.add(employee1);
        employees.add(employee2);

        System.out.println(employees);
        Collections.sort(employees);
        System.out.println(employees);

    }
}
