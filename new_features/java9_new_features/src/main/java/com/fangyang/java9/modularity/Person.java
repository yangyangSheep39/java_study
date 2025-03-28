package com.fangyang.java9.modularity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yangyangsheep
 * @Description 模块化测试-com.fangyang.java9.modularity.Person
 * @CreateTime 2025/3/28 15:36
 */
@Getter
@Setter
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
