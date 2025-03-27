package com.fangyang.java8.lambda.beginning.service.impl;

import com.fangyang.java8.entity.Employee;
import com.fangyang.java8.lambda.beginning.service.MyPredicate;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
