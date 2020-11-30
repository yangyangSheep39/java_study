package com.java8.new_time_api;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

public class TestLocalDateTIme {

    /**
     * LocalDate
     * LocalTime
     * LocalDateTime
     *      三者使用方法一样
     */
    @Test
    public void test() {
        //获取当前系统时间
        System.out.println("==============================获取当前系统时间==============================");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        //获取LocalDateTime 实例的另一种方法
        System.out.println("==============================获取LocalDateTime 实例的另一种方法==============================");
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 11, 30, 16, 05);
        System.out.println(localDateTime1);
        //运算  加上两年
        System.out.println("==============================运算  加上两年==============================");
        LocalDateTime localDateTime2 = localDateTime.plusYears(2);
        System.out.println(localDateTime2);
        //运算  减去5个月
        System.out.println("==============================运算  减去5个月==============================");
        LocalDateTime localDateTime3 = localDateTime.minusMonths(5);
        System.out.println(localDateTime3);
        //获取方法
        System.out.println("==============================获取方法==============================");
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonth());//获取月份的英文名称
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getDayOfMonth());
    }

    /**
     * 时间戳
     *      Instant 时间戳（以Unix元年，1970年1月1日00：00：00 到某个事件之间的毫秒值）
     *      默认获取UTC世界协调时间，格林尼治时间
     */
    @Test
    public void testInstant() {
        System.out.println("==============================获取当前UTC时间==============================");
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println("==============================带上时区偏移量==============================");
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println("==============================转换成毫秒值==============================");
        long l = instant.toEpochMilli();
        System.out.println(l);
        System.out.println("==============================加上60秒偏移量==============================");
        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println(instant1);
    }

    /**
     *     Duration： 计算两个"时间"之间的间隔
     *     Period： 计算两个"日期"之间的间隔
     */
    @Test
    public void testDur() {
        System.out.println("==============================两个时间戳之间的间隔（秒）==============================");
        Instant now = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant now1 = Instant.now();
        Duration duration = Duration.between(now, now1);
        System.out.println(duration);
        System.out.println("==============================两个时间之间的间隔（毫秒值）==============================");
        LocalTime localTime = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime localTime1 = LocalTime.now();
        Duration between = Duration.between(localTime, localTime1);
        System.out.println(between.toMillis());
        System.out.println("==============================两个日期之间的间隔==============================");
        LocalDate localDate = LocalDate.of(2019, 10, 29);
        LocalDate localDate1 = LocalDate.now();
        Period period = Period.between(localDate, localDate1);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    /**
     *      TemporalAdjuster：时间校正器
     */
    @Test
    public void testTem() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(10);
        System.out.println(localDateTime1);
        System.out.println("==============================获取下个周日==============================");
        LocalDateTime nextSunday = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(nextSunday);
        System.out.println("==============================自定义到下一个工作日==============================");
        LocalDateTime nextWorkDay = localDateTime.with(e -> {
            LocalDateTime ldt = (LocalDateTime) e;
            DayOfWeek dayOfWeek = ldt.getDayOfWeek();
            //如果是周五就加3天
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt.plusDays(2);
            } else {
                return ldt.plusDays(1);
            }
        });
        System.out.println(nextWorkDay);
    }

    /**
     * DateTimeFormatter：格式化时间/日期
     */
    @Test
    public void testFor() {
        DateTimeFormatter isoDateTime = DateTimeFormatter.ISO_DATE;
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        String format = localDateTime.format(isoDateTime);
        System.out.println(format);
        System.out.println("==============================自定义日期时间格式==============================");
        DateTimeFormatter isoDateTime1 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format1 = localDateTime.format(isoDateTime1);
        System.out.println(format1);
        System.out.println("==============================解析回时间格式==============================");
        LocalDateTime parse = localDateTime.parse(format1, isoDateTime1);
        System.out.println(parse);
    }

    /**
     *      ZonedDate、ZonedTime、ZonedDateTime
     *      带时区的日期时间api
     */
    @Test
    public void testZone() {
        //获取所有的时区
        System.out.println("==============================获取所有的时区==============================");
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

    @Test
    public void testZoneAPI() {
        //获取指定时区的时区
        System.out.println("==============================获取指定时区的时区==============================");
        LocalDateTime nowTokyo = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(nowTokyo);
        System.out.println("==============================带时区的时间和日期格式==============================");
        ZonedDateTime zonedDateTime = nowTokyo.atZone(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime zonedDateTime1 = nowTokyo.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime1);
    }
}
