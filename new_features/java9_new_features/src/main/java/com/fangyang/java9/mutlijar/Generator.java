package com.fangyang.java9.mutlijar;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author yangyangsheep
 * @Description 通用方法类-Java8
 * @CreateTime 2025/3/29 22:41
 */
public class Generator {

    public Set<String> createStrings() {
        Set<String> strings = new HashSet<>();
        strings.add("Java");
        strings.add("8");
        return strings;
    }
}
