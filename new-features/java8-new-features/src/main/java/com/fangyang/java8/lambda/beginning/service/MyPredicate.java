package com.fangyang.java8.lambda.beginning.service;

@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
