package com.java8.stream;

import com.java8.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 *  中间操作
 */
public class StreamMidOperate {
    List<Employee> employees = Arrays.asList(
            new Employee("李四", 37, 5555.55),
            new Employee("李四", 37, 5555.55),
            new Employee("李四", 37, 5555.55),
            new Employee("李四", 37, 5555.55),
            new Employee("王五", 14, 4444.44),
            new Employee("赵六", 25, 3333.33),
            new Employee("田七", 11, 2222.22),
            new Employee("张三", 39, 6666.66)
    );

    /**
     * 筛选和切片
     *      filter ( Predicate p )	接收Lambda，从流中排除某些元素
     *      distinct ( ) 	筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     *      limit ( long maxSize )	截断流，使其元素不超过给定数量
     *      skip ( long n )	跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与 limit ( n ) 互补
     *
     * 注意：
     *      中间操作不会进行任何操作，必须进行终止操作，终止操作一次性执行全部内容，即“惰性求值”，或“延迟加载”
     */
    //内部迭代，迭代操作由 Stream API 完成
    @Test
    public void test() {
        /*过滤*/
        Stream<Employee> stream = employees.stream()
                .filter((e) -> {
                    System.out.println("Stream API的中间操作");
                    return e.getAge() > 35;
                });
        //必须进行终止操作，不然中间操作没有任何结果
        stream.forEach(System.out::println);

        System.out.println("============================================================");

        /*截断流*/
        employees.stream()
                .limit(2) //找到所需要的数据之后，后续的操作就不再进行了
                .filter((e) -> {
                    System.out.println("短路  取前两个");
                    return e.getSalary() > 3000;
                })
                .forEach(System.out::println);

        System.out.println("============================================================");

        /*跳过元素*/
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路  取后两个");
                    return e.getSalary() > 3000;
                })
                .skip(2) //找到所需要的数据之后，后续的操作就不再进行了
                .forEach(System.out::println);

        System.out.println("============================================================");
        /*
         * 筛选
         * 必须重写 hashCode() 和 equals() 方法
         */
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路  取后两个  未去重");
                    return e.getSalary() > 3000;
                })
                .forEach(System.out::println);
        System.out.println("============================================================");
        employees.stream()
                .distinct()
                .filter((e) -> {
                    System.out.println("短路  取后两个  已去重");
                    return e.getSalary() > 3000;
                })
                .forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test2() {
        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 映射
     *      map ( Function f ) 	接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     *      mapToDouble ( ToDoubleFunction f )	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream
     *      mapToInt ( ToIntFunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 IntStream
     *      mapToLong ( ToLongFunction f)	接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 LongStream
     *      flatMap( Function f )	接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有的流连接成一个流
     */
    @Test
    public void test1() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff");
        List<String> list2 = Arrays.asList("111", "222", "333", "444", "555", "666");

        /*
        map映射
        批量进行数据操作
        */
        System.out.println("==============================map示例==============================");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("============================================================");
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        /*flatMap映射*/
        System.out.println("==============================flatMap示例==============================");
        Stream<Stream<Character>> streamStream = list.stream()
                .map(StreamMidOperate::filterCharacter); //map本身就是返回流,filterCharacter返回的也是流，嵌套循环
        streamStream.forEach((stream) -> stream.forEach(System.out::println));
        System.out.println("============================================================");
        //flatMap得到的其实是一个流,将多个流转换成一个流
        Stream<Character> characterStream = list.stream()
                .flatMap(StreamMidOperate::filterCharacter);
        characterStream.forEach(System.out::println);
        System.out.println("============================================================");
        list.stream()
                .flatMap(StreamMidOperate::filterCharacter)
                .forEach(System.out::println);
        System.out.println("==============================其他映射测试==============================");
        /*测试其他映射*/
        DoubleStream doubleStream = list2.stream()
                .mapToDouble(e -> Double.valueOf(e));
        doubleStream.forEach(System.out::println);
        System.out.println("============================================================");
        list2.stream()
                .mapToDouble(e -> Double.valueOf(e))
                .forEach(System.out::println);

    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    @Test
    public void testListAdd() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee", "fff");
        List list2 = new ArrayList<>();
        List list3 = new ArrayList<>();
        //add是把添加的参数作为一个元素添加到集合，而addAll则是提取出集合中的元素添加到集合当中
        list2.add(11);
        list2.add(22);
        list2.add(list);
        list3.addAll(list);

        System.out.println(list2);
        System.out.println("============================================================");
        System.out.println(list3);
    }

    /**
     * 排序
     *      sorted ( )	产生一个新流，其中按自然顺序排序(Comparable 按照字母顺序排序)
     *      sorted ( Comparator comp )	产生一个新流，其中按比较器顺序排序 (Comparator)
     */
    @Test
    public void testSort() {
        List<String> list = Arrays.asList("bbb", "eee", "ccc", "aaa", "ddd", "fff");
        System.out.println("==============================自然排序==============================");
        list.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("==============================定制排序==============================");
        employees.stream()
                .distinct()
                .sorted((e1, e2) -> {
                    //如果age的数据类型为int等基本数据类型，因为本身返回值是一个基本数据类型没有equals方法，会报错
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return -e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }

}

