package com.fangyang.java8.stream.serial_and_parallel;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class SerialAndParallelStream {

    /**
     * Java8并行流
     */
    @Test
    public void test3() {
        Instant startS = Instant.now();
        LongStream.rangeClosed(0, 100000000000L)
                .reduce(0, Long::sum);//顺序流，单线程的
        Instant endS = Instant.now();
        System.out.println("耗费时间为： " + Duration.between(startS, endS).toMillis());

        System.out.println("============================================================");

        Instant startP = Instant.now();
        LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);//顺序流，单线程的
        Instant endP = Instant.now();
        System.out.println("耗费时间为： " + Duration.between(startP, endP).toMillis());


        //耗费时间为： 59098
        //        ============================================================
        //耗费时间为： 23949
    }
}
