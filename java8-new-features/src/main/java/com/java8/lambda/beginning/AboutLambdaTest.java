package com.java8.lambda.beginning;

import org.junit.jupiter.api.Test;

import java.util.*;

public class AboutLambdaTest {

    //原来的匿名内部类
    @Test
    public void testAnonymousInnerClass() {
        Comparator com = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                //匿名内部类有效的代码其实就这一句
                //但是为了完成这一句，上下需要写很多
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }

    //转变成Lambda表达式的写法
    @Test
    public void testLambda() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet = new TreeSet<>(com);
    }

}
