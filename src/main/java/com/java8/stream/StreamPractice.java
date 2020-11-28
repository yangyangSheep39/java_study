package com.java8.stream;

import com.java8.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamPractice {
    List<Employee> employees = Arrays.asList(
            new Employee("李四", 37, 5555.55),
            new Employee("王五", 14, 4444.44),
            new Employee("赵六", 25, 3333.33),
            new Employee("田七", 11, 2222.22),
            new Employee("张三", 39, 6666.66)
    );

    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表
     * 给定【1，2，3，4，5】，应该返回【1，4，9，16，25】
     */
    @Test
    public void testSquare() {
        Integer[] integers = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(integers)
                .map(x -> x * x)
                .forEach(System.out::println);
    }

    /**
     * 怎么用map和reduce 方法数一数流中由多少个Employee呢
     */
    @Test
    public void testMapAndReduce() {

        Optional<Integer> reduce = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());
    }
}
