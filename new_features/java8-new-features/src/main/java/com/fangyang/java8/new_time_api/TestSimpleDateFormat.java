package com.fangyang.java8.new_time_api;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class TestSimpleDateFormat {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Date> callable = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return DateFormatTreadLock.convert("2016-12-18");
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            result.add(pool.submit(callable));
        }

        for (Future<Date> dateFuture : result) {
            System.out.println(dateFuture.get());
        }

        pool.shutdown();
    }

    @Test
    public void testLocalDate() throws ExecutionException, InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Callable<LocalDate> callable = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("2016-08-29", dateTimeFormatter);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            result.add(pool.submit(callable));
        }

        for (Future<LocalDate> dateFuture : result) {
            System.out.println(dateFuture.get());
        }

        pool.shutdown();
    }
}
