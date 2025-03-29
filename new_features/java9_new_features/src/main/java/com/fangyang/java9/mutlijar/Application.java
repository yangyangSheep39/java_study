package com.fangyang.java9.mutlijar;

/**
 * @Author yangyangsheep
 * @Description Java8应用类
 * @CreateTime 2025/3/29 22:43
 */
public class Application {

    public static void testMutliJar() {
        Generator generator = new Generator();
        System.out.println("Generator String , Current Use Version Is : " + generator.createStrings());
    }
}
