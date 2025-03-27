package com.fangyang.java8.method_and_constructor_reference;


import com.fangyang.java8.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器引用：
 *
 * 语法格式：
 *      ClassName::new
 *
 * 注意：
 *      需要调用的构造器的参数列表，需要与函数式接口的抽象方法的参数列表保持一致
 */
public class ConstructorRefTest {

    @Test
    public void test() {
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());

        System.out.println("============================================================");

        //构造器的引用方式(自动匹配无参构造器)
        Supplier<Employee> sup1 = Employee::new;
        System.out.println(sup1.get());

        System.out.println("============================================================");

        //找一个参数的构造器
        Function<String, Employee> function = (x) -> new Employee(x);
        Employee employee = function.apply("张三");
        System.out.println(employee);

        Function<String, Employee> function1 = Employee::new;
        Employee employee1 = function1.apply("李四");
        System.out.println(employee1);

        //调用两个参数的构造器
        BiFunction<String, Integer, Employee> biFunction = Employee::new;
        Employee employee2 = biFunction.apply("王五", 20);
        System.out.println(employee2);
    }

}
