package com.java8.lambda.beginning.service.impl;

import com.java8.entity.Employee;
import com.java8.lambda.beginning.service.MyPredicate;

public class FilterEmployeeBySalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 4000;
    }
}
