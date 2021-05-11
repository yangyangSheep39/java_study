package com.fangyang.demo.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @author yangyangSheep
 * @ClassName UseStreamDealWithMapAndSorted.java
 * @Description 使用Stream处理Map比对两个集合，并且排序
 * @createTime 2021/5/11 20:14
 */
public class UseStreamDealWithMapAndSorted {
    public static void main(String[] args) {
        //基准集合
        List<String> list = Arrays.asList("202101", "202102", "202103", "202104", "202105", "202106", "202107");
        //数据集合
        List<Map<String, Object>> mapList = Lists.newArrayList();
        //造数据
        HashMap<String, Object> map1 = Maps.newHashMap();
        map1.put("month", "202101");
        map1.put("count", 1);
        mapList.add(map1);
        HashMap<String, Object> map2 = Maps.newHashMap();
        map2.put("month", "202102");
        map2.put("count", 2);
        mapList.add(map2);
        HashMap<String, Object> map4 = Maps.newHashMap();
        map4.put("month", "202104");
        map4.put("count", 4);
        mapList.add(map4);
        HashMap<String, Object> map6 = Maps.newHashMap();
        map6.put("month", "202106");
        map6.put("count", 6);
        mapList.add(map6);

        deal(list, mapList);
        sortAsc(mapList);
        mapList.forEach(System.out::println);
        System.out.println("============================================================");
        sortDesc(mapList);
        mapList.forEach(System.out::println);
    }

    /**
     * 处理map集合数据
     */
    private static void deal(List<String> nativeMonthAndPastSixMon, List<Map<String, Object>> mapList) {
        nativeMonthAndPastSixMon.forEach(e -> {
            Optional<Map.Entry<String, Object>> first = mapList.stream().map(Map::entrySet).flatMap(Set::stream).filter(x -> e.equals(x.getValue())).findFirst();
            if (!first.isPresent()) {
                Map<String, Object> maps = new HashMap<>();
                maps.put("month", e);
                maps.put("count", 0);
                mapList.add(maps);
            }
        });
    }

    /**
     * 升序
     */
    private static void sortAsc(List<Map<String, Object>> mapList) {
        Collections.sort(mapList, (o1, o2) -> {
            Integer i1 = Integer.valueOf(o1.get("month").toString());
            Integer i2 = Integer.valueOf(o2.get("month").toString());
            //升序
            if (i1 != null) {
                return i1.compareTo(i2);
            }
            return 0;
        });
    }

    /**
     * 降序
     */
    private static void sortDesc(List<Map<String, Object>> mapList) {
        Collections.sort(mapList, (o1, o2) -> {
            Integer i1 = Integer.valueOf(o1.get("month").toString());
            Integer i2 = Integer.valueOf(o2.get("month").toString());
            //降序
            if (i2 != null) {
                return i2.compareTo(i1);
            }
            return 0;
        });
    }
}
