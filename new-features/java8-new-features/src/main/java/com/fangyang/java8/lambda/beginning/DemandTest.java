package com.fangyang.java8.lambda.beginning;

import com.fangyang.java8.entity.Employee;
import com.fangyang.java8.lambda.beginning.service.MyPredicate;
import com.fangyang.java8.lambda.beginning.service.impl.FilterEmployeeByAge;
import com.fangyang.java8.lambda.beginning.service.impl.FilterEmployeeBySalary;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemandTest {
    List<Employee> employees = Arrays.asList(
            new Employee("李四", 19, 5555.55),
            new Employee("王五", 20, 4444.44),
            new Employee("赵六", 25, 3333.33),
            new Employee("田七", 38, 2222.22),
            new Employee("张三", 39, 1111.11)
    );

    /**
     * 测试不进行任何优化的操作代码
     */
    @Test
    public void testFilterEmployee() {
        List<Employee> employeesMoreThan35 = getEmployees(employees);
        for (Employee employee : employeesMoreThan35) {
            System.out.println(employee);
        }
        System.out.println("============================================================");
        List<Employee> employees2 = getEmployees2(employees);
        for (Employee employee : employees2) {
            System.out.println(employee);
        }
    }

    /**
     * 两个需求的区别只有一句话，但是就会出现大量的冗余代码
     * 不进行任何优化的操作方式
     * @param employees
     * @return
     */
    //需求案例，获取当前公司中员工年龄大于35的员工信息
    public List<Employee> getEmployees(List<Employee> employees) {
        List<Employee> employeeList = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getAge() >= 35) {
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    //需求案例，获取当前公司中工资高于4000的员工信息
    public List<Employee> getEmployees2(List<Employee> employees) {
        List<Employee> employeeList = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getSalary() >= 4000) {
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    /**
     * 实现过滤方法的接口   对代码进行优化
     *
     * @param employees   数据集合
     * @param myPredicate 过滤方法实现类
     * @return
     */
    public List<Employee> getEmployeesWithOptimize(List<Employee> employees, MyPredicate<Employee> myPredicate) {
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : employees) {
            if (myPredicate.test(employee)) {
                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    /**
     * 优化方式一： 策略设计模式
     * 利用接口的方式，提取出符合的数据
     * 但是每次使用策略都需要重新写一个类实现接口，很麻烦
     */
    @Test
    public void testFilterWithOptimize1() {
        List<Employee> employeesWithOptimize = getEmployeesWithOptimize(employees, new FilterEmployeeByAge());
        for (Employee employee : employeesWithOptimize) {
            System.out.println(employee);
        }

        System.out.println("============================================================");

        List<Employee> employeesWithOptimize1 = getEmployeesWithOptimize(employees, new FilterEmployeeBySalary());
        for (Employee employee : employeesWithOptimize1) {
            System.out.println(employee);
        }

    }

    /**
     * 优化方式二： 匿名内部类
     */
    @Test
    public void testWithInnerClass() {
        List<Employee> list = getEmployeesWithOptimize(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 4000;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式三： Lambda表达式
     */
    @Test
    public void testByLambda() {
        List<Employee> list = getEmployeesWithOptimize(employees, (e) -> e.getSalary() >= 4000);
        list.forEach(System.out::println);
    }

    /**
     * 优化方式四： 假设接口接口实现类都没有，仅有一个数据集合
     * 使用Lambda表达式获取
     */
    @Test
    public void testByLambdaWithOnlyData() {
        employees.stream()
                .filter((e) -> e.getSalary() >= 4000)
                .limit(1)
                .forEach(System.out::println);

        System.out.println("============================================================");

        //Stream API
        employees.stream()
                .map((Employee::getName))
                .forEach(System.out::println);
    }
}
