package com.java8.default_and_static_method;

/**
 * 接口中的静态方法和默认方法
 */
public interface DefaultAndStaticMethodInterface {

    default String getName() {
        return "接口冲突实现";
    }
}
