package com.fangyang.java9;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Author yangyangsheep
 * @Description 新的StreamApi测试
 * @CreateTime 2025/4/1 00:14
 */
public class NewStreamApiTest {

    public static void main(String[] args) {
        testTakeWhile();
        System.out.println("============================================================");
        testDropWhile();
        System.out.println("============================================================");
        testOfNullable();
        System.out.println("============================================================");
        testIterate();
        System.out.println("============================================================");
        testOptionalStream();
    }

    /**
     * takeWhile()方法 与 dropWhile()方法 互补
     * 1. 该方法返回一个Stream，该Stream包含满足给定条件的元素，直到第一个不满足条件的元素为止。
     * 2. 该方法是一个短路操作，即在找到第一个不满足条件的元素后，后续的元素将不会被处理。
     * 3. 该方法在处理元素时是顺序的，即从Stream的开头开始处理元素。
     * 4. 该方法在处理元素时是懒惰的，即只有在需要时才会处理元素。
     */
    private static void testTakeWhile() {
        List<Integer> list = Arrays.asList(11, 22, 33, 11, 55);
        list.stream()
                .takeWhile(i -> i < 33)
                .forEach(System.out::println);
    }

    /**
     * dropWhile()方法 与 takeWhile()方法 互补
     * 1. 该方法返回一个Stream，该Stream包含跳过满足给定条件的元素后的所有元素。
     * 2. 该方法是一个短路操作，即在找到第一个不满足条件的元素后，后续的元素将会被处理。
     * 3. 该方法在处理元素时是顺序的，即从Stream的开头开始处理元素。
     * 4. 该方法在处理元素时是懒惰的，即只有在需要时才会处理元素。
     * 5. 该方法在处理元素时是并行的，即可以在多个线程中处理元素。
     */
    private static void testDropWhile() {
        List<Integer> list = Arrays.asList(11, 22, 33, 11, 55);
        // 跳过小于33的元素
        list.stream()
                .dropWhile(i -> i < 33)
                .forEach(System.out::println);
    }

    /**
     * ofNullable()方法
     * 1. 该方法返回一个Stream，该Stream包含给定元素，如果元素为null，则返回一个空的Stream。
     * 2. 该方法是一个短路操作，即在找到第一个不满足条件的元素后，后续的元素将不会被处理。
     * 3. 该方法在处理元素时是顺序的，即从Stream的开头开始处理元素。
     * 4. 该方法在处理元素时是懒惰的，即只有在需要时才会处理元素。
     * 5. 该方法在处理元素时是并行的，即可以在多个线程中处理元素。
     */
    private static void testOfNullable() {
        // Stream中如果有多个元素，是允许其中一个为null的
        Stream<String> stream = Stream.of("aa", "bb", "cc", "dd", null);
        stream.forEach(System.out::println);
        // 如果Stream中只有一个元素为null，则会抛出NullPointerException异常
        //Stream<String> stream2 = Stream.of(null);
        //stream2.forEach(System.out::println);

        //ofNullable()方法
        Stream<Object> objectStream = Stream.ofNullable(null);
        objectStream.forEach(System.out::println);
        //这里不能打印了，因为流已经被关闭了

        Stream<Object> objectStream2 = Stream.ofNullable(null);
        System.out.println(objectStream2.count());
        //同理关闭了
    }

    /**
     * iterate()方法
     * 1. 该方法返回一个Stream，该Stream包含给定元素和后续的元素。
     * 2. 该方法是一个短路操作，即在找到第一个不满足条件的元素后，后续的元素将不会被处理。
     * 3. 该方法在处理元素时是顺序的，即从Stream的开头开始处理元素。
     * 4. 该方法在处理元素时是懒惰的，即只有在需要时才会处理元素。
     * 5. 该方法在处理元素时是并行的，即可以在多个线程中处理元素。
     * 6. 该方法在处理元素时是无限的，即可以无限地生成元素。
     * 7. 该方法在处理元素时是递归的，即可以递归地生成元素。
     * 8. 该方法在处理元素时是迭代的，即可以迭代地生成元素。
     */
    private static void testIterate() {
        // 这里会无限循环，因为没有终止条件，在8的时候需要指定limit才可以
        //Stream.iterate(0, i -> i + 1).forEach(System.out::println);
        Stream.iterate(0, i -> i + 1).limit(10).forEach(System.out::println);
        //Java9 新增重载的iterate()方法，可以通过判断语句指定结束条件
        Stream.iterate(1, x -> x < 5, i -> i + 1).forEach(System.out::println);
    }

    /**
     * Optional.stream()方法
     * 1. 该方法返回一个Stream，该Stream包含给定Optional中的元素，如果Optional为空，则返回一个空的Stream。
     * 2. 该方法是一个短路操作，即在找到第一个不满足条件的元素后，后续的元素将不会被处理。
     */
    private static void testOptionalStream() {
        List<String> aa = List.of("aa", "bb", "cc", "dd");
        Optional<List<String>> aa1 = Optional.ofNullable(aa);
        aa1.ifPresent(System.out::println);

        Stream<List<String>> stream = aa1.stream();
        //这里是返回1,因为整个optional是一个流
        System.out.println(stream.count());
        //stream.forEach(System.out::println);

        Stream<String> stream1 = aa1.stream().flatMap(Collection::stream);
        System.out.println(stream1.count());
    }
}
