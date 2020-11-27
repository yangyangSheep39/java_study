package com.java8.stream;


import com.java8.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  终止操作
 */
public class StreamEndOperate {
    List<Employee> employees = Arrays.asList(
            new Employee("李四", 37, 5555.55, Employee.Status.FREE),
            new Employee("王五", 14, 4444.44, Employee.Status.BUSY),
            new Employee("赵六", 25, 3333.33, Employee.Status.VOCATION),
            new Employee("田七", 11, 2222.22, Employee.Status.FREE),
            new Employee("张三", 39, 6666.66, Employee.Status.BUSY)
    );
    List<Employee> employees2 = Arrays.asList(
            new Employee("王五", 14, 4444.44, Employee.Status.BUSY),
            new Employee("张三", 39, 6666.66, Employee.Status.BUSY)
    );

    List<Employee> employees3 = Arrays.asList(
            new Employee("李四", 37, 5555.55, Employee.Status.FREE),
            new Employee("李四", 37, 5555.55, Employee.Status.FREE),
            new Employee("李四", 37, 5555.55, Employee.Status.FREE),
            new Employee("李四", 37, 5555.55, Employee.Status.FREE),
            new Employee("王五", 14, 4444.44, Employee.Status.BUSY),
            new Employee("赵六", 25, 3333.33, Employee.Status.VOCATION),
            new Employee("田七", 11, 2222.22, Employee.Status.FREE),
            new Employee("张三", 39, 6666.66, Employee.Status.BUSY)
    );

    /**
     * 查找和匹配
     *       allMatch	检查是否匹配到所有元素
     *       anyMatch	检查是否至少匹配到一个元素
     *       noneMatch	检查是否没有匹配到所有元素
     *       findFirst	返回第一个元素
     *       findAny	返回当前流中的任意元素
     *       count	返回流中匹配到的元素的总个数
     *       max	返回流中的最大值
     *       min	返回流中的最小值
     */
    @Test
    public void test() {
        /*是否匹配所有元素*/
        boolean b = employees.stream()
                .allMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);
        boolean b2 = employees2.stream()
                .allMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        System.out.println("============================================================");
        /*检查是否至少匹配一个元素*/
        boolean b3 = employees.stream()
                .anyMatch(employee -> employee.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);
        System.out.println("============================================================");
        /*检查是否没有匹配所有元素*/
        boolean b4 = employees.stream()
                .noneMatch(employee -> employee.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b4);
        boolean b5 = employees2.stream()
                .noneMatch(employee -> employee.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b5);
        System.out.println("============================================================");
        /*返回第一个元素*/
        Optional<Employee> first = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        //orElse() -- 如果说封装的对象为空，就用一个替代的对象
        //first.orElse(employees);
        System.out.println(first.get());
        System.out.println("============================================================");
        /*返回任意一个元素*/
        Optional<Employee> any = employees.stream()//获取到一个串行流
                .filter(employee -> employee.getStatus().equals(Employee.Status.FREE))
                .findAny();
        Optional<Employee> any2 = employees3.parallelStream()//获取到一个并行流，多个线程同时找
                .filter(employee -> employee.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(any.get());
        System.out.println(any2.get());
        System.out.println("============================================================");
        /*返回流中匹配到的元素的总个数*/
        long count = employees.stream()
                .count();
        System.out.println(count);
        System.out.println("============================================================");
        /*获取最大值*/
        //获取工资最高的员工信息
        Optional<Employee> max = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());
        System.out.println("============================================================");
        /*获取最小值*/
        //获取工资最少的工资是多少
        Optional<Double> min = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(min.get());
    }

    /**
     * 归约
     *      reduce(T identity,BinaryOperator)/reduce(BinaryOperator)  可以将流中的元素反复结合起来，得到一个值
     */
    @Test
    public void testReduce() {
        //归约操作先将起始值作为x，然后从流中取一个值作为y，然后将运算结果再作为x，再取值，往复
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);//(起始值,二元运算)
        System.out.println(sum);
        System.out.println("============================================================");
        //计算总工资是多少
        Optional<Double> sumSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(sumSalary.get());
    }

    /**
     * 收集
     *      collect 将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中的元素做汇总的方法
     */
    @Test
    public void testCollect() {
        System.out.println("==============================收集到常用的集合当中==============================");
        employees3.stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("============================================================");
        //收集到set去重
        employees3.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
        System.out.println("==============================收集到特殊的集合当中==============================");
        //收集到特殊的集合当中
        HashSet<String> collect = employees3.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        collect.forEach(System.out::println);
        System.out.println("============================================================");
        LinkedHashSet<String> collect1 = employees3.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        collect1.forEach(System.out::println);

    }

    /**
     * 其他的收集情况
     *      总数
     *      平均值
     *      总和
     *      两种方式  直接返回总数，总和，最小值，平均值，最大值
     *      工资最大值员工信息
     *      最少工资值
     *      连接
     */
    @Test
    public void testCollect2() {
        System.out.println("==============================总数==============================");
        //总数
        Long collect2 = employees.stream()
                .collect(Collectors.counting());
        System.out.println(collect2);
        System.out.println("==============================平均值==============================");
        //平均值
        Double collect3 = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(collect3);
        System.out.println("==============================总和==============================");
        //总和
        Double collectDouble = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(collectDouble);
        System.out.println("==========两种方式  直接返回总数，总和，最小值，平均值，最大值==========");
        /*两种方式  直接返回总数，总和，最小值，平均值，最大值*/
        DoubleSummaryStatistics collect4 = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect4);
        DoubleSummaryStatistics doubleSummaryStatistics = employees.stream()
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();
        System.out.println(doubleSummaryStatistics);
        System.out.println("==============================工资最大值员工信息==============================");
        //工资最大值员工信息
        Optional<Employee> collect5 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(collect5.get());
        System.out.println("==============================最少工资值==============================");
        //最少工资值
        Optional<Double> collect6 = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.minBy(Double::compare));
        System.out.println(collect6.get());
        System.out.println("==============================连接==============================");
        //连接
        String collect = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",", "===", "==="));//,隔开  后两个不传参会自动去掉首尾
        System.out.println(collect);
    }

    /**
     * 其他的收集情况
     *      分组
     *      多级分组
     *      分区
     */
    @Test
    public void testCollect3() {
        System.out.println("==============================分组==============================");
        //分组（返回一个Map<分组依据，数据>）
        Map<Employee.Status, List<Employee>> collect7 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        for (Map.Entry<Employee.Status, List<Employee>> m : collect7.entrySet()) {
            System.out.println("key:" + m.getKey());
            m.getValue().stream().forEach(System.out::println);
        }
        System.out.println("==============================多级分组==============================");
        //多级分组（返回一个Map<分组依据，数据>）可以无限级分组
        Map<Employee.Status, Map<String, List<Employee>>> collect8 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        for (Map.Entry<Employee.Status, Map<String, List<Employee>>> m : collect8.entrySet()) {
            System.out.println("key:" + m.getKey());
            for (Map.Entry<String, List<Employee>> mm : m.getValue().entrySet()) {
                System.out.println("InsideKey:" + mm.getKey());
                mm.getValue().stream().forEach(System.out::println);
            }
        }
        System.out.println("==============================分区==============================");
        Map<Boolean, List<Employee>> collect = employees.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 4000));
        for (Map.Entry<Boolean, List<Employee>> booleanListEntry : collect.entrySet()) {
            System.out.println("key:" + booleanListEntry.getKey());
            booleanListEntry.getValue().stream().forEach(System.out::println);
        }

    }

    /**
     * 给定一些数字数组，返回由每个数字的平方组成的列表
     */
    @Test
    public void practice() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5};
        Arrays.stream(array)
                .map(x -> x * x)
                .forEach(System.out::println);
    }

    /**
     * 利用 map 和 reduce 方法数一数流中有多少个Employee
     */
    @Test
    public void practice2() {
        Optional<Integer> reduce = employees.stream()
                .map(e -> 1)
                .reduce(Integer::sum);
        System.out.println(reduce.get());
    }
}

