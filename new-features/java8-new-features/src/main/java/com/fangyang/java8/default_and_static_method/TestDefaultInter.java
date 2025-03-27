package com.fangyang.java8.default_and_static_method;

import org.junit.jupiter.api.Test;

public class TestDefaultInter {

    /**
     * 类优先原则
     * @param args
     */
    @Test
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        //此处遵循类优先原则，调用的是类的方法
        System.out.println(subClass.getName());
    }

    /**
     * 接口冲突
     */
    @Test
    public void test() {
        SubClassNoExtend subClass = new SubClassNoExtend();
        //此处接口冲突，需要具体实现方法覆盖，调用的是类的方法
        System.out.println(subClass.getName());
    }

    /**
     * 静态方法
     */
    @Test
    public void testStatic() {
        String show = DefaultAndStaticMethod.show();
        System.out.println(show);
    }

}

