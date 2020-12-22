package com.java8.lambda.practice.interf;

@FunctionalInterface
public interface MyFunctionLong<T, R> {
    R getValue(T num1, T num2);
}
