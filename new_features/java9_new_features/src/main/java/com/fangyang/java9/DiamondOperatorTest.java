package com.fangyang.java9;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author yangyangsheep
 * @Description 钻石（泛型）操作符测试
 * @CreateTime 2025/3/31 22:48
 */
public class DiamondOperatorTest {

    Set<String> set = new HashSet<>() {
        private static final long serialVersionUID = 1L;

        {
            add("AA");
            add("BB");
            add("CC");
            add("DD");
        }
    };

    List<String> list = new ArrayList<>() {
        private static final long serialVersionUID = 1L;

        {
            add("AA");
            add("BB");
            add("CC");
            add("DD");
        }
    };

    public static void main(String[] args) {
        DiamondOperatorTest diamondOperatorTest = new DiamondOperatorTest();
        System.out.println(diamondOperatorTest.set);
        System.out.println(diamondOperatorTest.list);
    }
}
