package com.fangyang.java8.new_time_api;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTreadLock {
    private static final ThreadLocal<DateFormat> d = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static Date convert(String source) throws ParseException {
        return d.get().parse(source);
    }
}
