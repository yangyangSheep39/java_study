package com.fangyang.java9.test;

//未导入无法引用
//import com.fangyang.java9.bean.User;

import com.fangyang.java9.modularity.Person;

/**
 * @Author yangyangsheep
 * @Description 模块化测试的另一个类
 * @CreateTime 2025/3/28 15:47
 */
public class ModularityTest {

    public static void main(String[] args) {
        //在同一个模块当中是可以使用的
        Person person = new Person("John Doe", 30);
        System.out.println("The message from Modularity Test!!!!!!!!");
        System.out.println("Name: " + person.getName() + ", Age: " + person.getAge());
        //未导入无法引用
        //User user  = new User("John Doe", 30);
    }
}
