package com.java8.lambda.practice;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8内置的四大核心函数式接口
 *
 * Consumer<T>：消费型接口 （消费指的是无返回值，有去无回）
 *      void accept(T t)
 *
 * Supplier<T>：供给型接口 （获取内容/对象/参数）
 *      T  get();
 *
 * Function<T,R>：函数型接口（传进参数进行一些处理，返回）
 *      R apply(T t);
 *
 * Predicate<T>：断言型接口 (用于做一些判断操作)
 *      boolean test(T t);
 */
public class Java8CoreFunctionInter {

    /**
     * Consumer<T>：消费型接口
     */
    @Test
    public void testCost() {
        cost(10000, (m) -> System.out.println("大宝剑，消费" + m + "元"));
    }

    public void cost(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * Supplier<T>：供给型接口
     */
    @Test
    public void getSup() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer integer : numList) {
            System.out.println(integer);
        }
    }

    //需求，产生指定个数整数，并且放入集合中
    public List<Integer> getNumList(int numCount, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numCount; i++) {
            Integer integer = supplier.get();
            list.add(integer);
        }
        return list;
    }

    /**
     * Function<T,R>：函数型接口
     */
    @Test
    public void testStrHandler() {
        String strHandler = strHandler("\t\t\t Lambda表达式威武", str -> str.trim());
        System.out.println(strHandler);

        System.out.println("============================================================");

        String strHandler1 = strHandler("威武Lambda表达式", str -> str.substring(2, 8));
        System.out.println(strHandler1);
    }

    //需求，用于处理字符串
    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * Predicate<T>：断言型接口
     */
    @Test
    public void testFilterString() {
        List<String> list = Arrays.asList("Hello", "Lambda", "fy", "Java", "Python", "Ok");
        //过滤出长度大于3的字符串
        List<String> stringList = filterString(list, s -> s.length() > 3);
        for (String s : stringList) {
            System.out.println(s);
        }
    }

    //需求，将指定的满足条件的字符串添加到集合中
    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        List<String> stringList = new ArrayList<>();
        for (String s : list) {
            //如果满足条件，就添加到集合当中
            if (predicate.test(s)) {
                stringList.add(s);
            }
        }
        return stringList;
    }
}
