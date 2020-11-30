package com.java8.stream.serial_and_parallel;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TestForkJoin {

    /**
     * Fork/Join的执行需要一个池        ForkJoinPool的支持
     */
    @Test
    public void test() {
        //Java8的时间计算
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 50000000000L);
        Long invoke = pool.invoke(task);
        System.out.println(invoke);

        Instant end = Instant.now();

        System.out.println("耗费时间为： " + Duration.between(start, end).toMillis());//毫秒   126444
        //System.out.println("耗费时间为： " + Duration.between(start, end).getNano());//纳秒
        //System.out.println("耗费时间为： " + Duration.between(start, end).getSeconds());//秒
    }

    /**
     * 普通的for循环效率
     */
    @Test
    public void test2() {
        Instant start = Instant.now();

        long sum = 0L;
        for (long i = 0; i < 50000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为： " + Duration.between(start, end).toMillis());//毫秒  21059
    }
}
