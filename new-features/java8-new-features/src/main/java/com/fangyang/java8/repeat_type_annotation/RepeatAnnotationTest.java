package com.fangyang.java8.repeat_type_annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 重复注解和类型注解
 */
public class RepeatAnnotationTest {
    /**
     * 重复注解必须要使用容器管理
     * @param string
     */
    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show(@MyAnnotation String string) {//指定了TYPE_PARAMETER之后就可以注解类型

    }

    @MyAnnotation("Hello")
    @MyAnnotation("World")
    public void show1() {//指定了TYPE_PARAMETER之后就可以注解类型

    }

    @Test
    public void test() throws NoSuchMethodException {
        Class<RepeatAnnotationTest> clazz = RepeatAnnotationTest.class;
        Method method = clazz.getMethod("show", new Class[]{String.class});
        MyAnnotation[] annotationsByType = method.getAnnotationsByType(MyAnnotation.class);
        Arrays.stream(annotationsByType).map(e -> e.value()).forEach(System.out::println);
        System.out.println("============================================================");
        Method method1 = clazz.getMethod("show1");
        MyAnnotation[] annotationsByType1 = method1.getAnnotationsByType(MyAnnotation.class);
        Arrays.stream(annotationsByType1).map(e -> e.value()).forEach(System.out::println);
    }
}
