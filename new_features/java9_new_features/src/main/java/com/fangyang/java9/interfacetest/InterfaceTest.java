package com.fangyang.java9.interfacetest;


/**
 * @Author yangyangsheep
 * @Description 接口测试
 * @CreateTime 2025/3/29 23:37
 */
public interface InterfaceTest {
    default void test() {
        System.out.println("test default");
    }


    static void test1() {
        System.out.println("test static");
    }


    private void test2() {
        System.out.println("test private");
    }

}
