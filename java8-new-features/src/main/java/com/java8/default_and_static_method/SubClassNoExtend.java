package com.java8.default_and_static_method;

public class SubClassNoExtend implements DefaultAndStaticMethod, DefaultAndStaticMethodInterface {

    @Override
    public String getName() {
        return DefaultAndStaticMethodInterface.super.getName();
    }
}
