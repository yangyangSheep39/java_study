package com.hashmap;

import java.util.Optional;

public class Test {

    public static void main(String[] args) {
        String s = "null";

        Optional.ofNullable(s).orElseThrow(() -> new RuntimeException("当前值为null"));
        Optional.ofNullable(s)
                .ifPresent(
                        data -> {
                            throw new RuntimeException("已存在当前值");
                        });
    }
}
