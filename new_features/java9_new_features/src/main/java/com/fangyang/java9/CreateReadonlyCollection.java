package com.fangyang.java9;

import java.util.*;

/**
 * @Author yangyangsheep
 * @Description 创建一个只读集合
 * @CreateTime 2025/3/31 23:52
 */
public class CreateReadonlyCollection {
    public static void main(String[] args) {
        testCreateReadonlyCollectionByJDK8();
        testCreateReadonlyCollectionByJDK9();
    }

    /**
     * 创建一个只读集合 JDK8的创建方法
     * 1. List：这个方法创建的也是一个只读List
     * 2. Set：这个方法创建的也是一个只读Set
     * 3. Map：这个方法创建的也是一个只读Map
     */
    private static void testCreateReadonlyCollectionByJDK9() {
        //Java9 创建只读List的方法
        List<Integer> integers = List.of(1, 2, 3);
        //integers.add(4); //不能执行，否则报异常
        integers.forEach(System.out::println);

        //Java9 创建只读Set的方法
        Set<Integer> integers1 = Set.of(1, 2, 3);
        //integers1.add(4); //不能执行，否则报异常
        integers1.forEach(System.out::println);

        //Java9 创建只读Map的方法
        Map<String, Integer> map = Map.of("AA", 1, "BB", 2, "CC", 3, "DD", 4);
        //map.putIfAbsent("EE", 5);
        map.forEach((k, v) -> System.out.println(k + " : " + v));

        //Java9 创建只读Map的方法2
        Map<String, Integer> map1 = Map.ofEntries(Map.entry("AA", 1),
                Map.entry("BB", 2),
                Map.entry("CC", 3),
                Map.entry("DD", 4));
        //map1.putIfAbsent("EE", 5);
        map1.forEach((k, v) -> System.out.println(k + " : " + v));
    }


    /**
     * 创建一个只读集合 JDK8的创建方法
     * 1. List：这个方法创建的也是一个只读List
     * 2. Set：这个方法创建的也是一个只读Set
     * 3. Map：这个方法创建的也是一个只读Map
     */
    public static void testCreateReadonlyCollectionByJDK8() {
        List<String> list = new ArrayList<>() {
            private static final long serialVersionUID = 1L;

            {
                add("AA");
                add("BB");
                add("CC");
                add("DD");
            }
        };
        //调用 Collections 中的方法，将List变为只读的
        List<String> newList = Collections.unmodifiableList(list);
        //newList.add("Tim");//不能执行，否则报异常
        newList.forEach(System.out::println);

        //List：这个方法创建的也是一个只读List
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        //list1.add(3);
        list1.forEach(System.out::println);

        //Set：这个方法创建的也是一个只读Set
        Set<Integer> integers = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 2, 3)));
        //integers.add(4);
        integers.forEach(System.out::println);

        //Map：这个方法创建的也是一个只读Map
        //Map和集合是同层级的,Map无序
        Map<Object, Object> objectObjectMap = Collections.unmodifiableMap(new HashMap<>() {
            {
                put("AA", 1);
                put("BB", 2);
                put("CC", 3);
                put("DD", 4);
            }
        });
        objectObjectMap.forEach((k, v) -> System.out.println(k + " : " + v));
    }

}
