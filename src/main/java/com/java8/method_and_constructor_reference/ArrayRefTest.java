package com.java8.method_and_constructor_reference;


import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * 数组引用：
 */
public class ArrayRefTest {

    @Test
    public void test() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] strings = function.apply(10);
        System.out.println(strings.length);

        System.out.println("============================================================");
        //数组引用的方式
        Function<Integer, String[]> function2 = String[]::new;
        String[] strings1 = function.apply(20);
        System.out.println(strings1.length);
    }

}
