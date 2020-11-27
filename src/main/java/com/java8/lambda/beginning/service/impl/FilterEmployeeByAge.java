package com.java8.lambda.beginning.service.impl;

import com.java8.entity.Employee;
import com.java8.lambda.beginning.service.MyPredicate;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
