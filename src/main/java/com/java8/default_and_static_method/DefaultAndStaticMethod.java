package com.java8.default_and_static_method;

/**
 * 接口中的静态方法和默认方法
 */
public interface DefaultAndStaticMethod {

    default String getName() {
        return "接口中的默认方法";
    }

    static String show() {
        return "接口中的静态方法";
    }
}
