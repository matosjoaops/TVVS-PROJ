package com.todotxt.todotxttouch.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RelativeDateTest {

    @ParameterizedTest
    @MethodSource("dateProvider")
    public void getRelativeDate(Date date, String expectedResult) {

        String result = RelativeDate.getRelativeDate(date);

        System.out.println(result);

        assertEquals(result, expectedResult);
    }

    static Stream<Arguments> dateProvider() {
        Calendar calendar = new GregorianCalendar();


        Date now = new Date();
        Date before = new Date(now.getTime() - 10000);
        Date after = new Date(now.getTime() + 10000);

        calendar.setTime(now);
        String nowString =
                calendar.get(Calendar.YEAR) + "-" +
                calendar.get(Calendar.MONTH) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH);

        /*calendar.setTime(before);
        String beforeString =
                calendar.get(Calendar.YEAR) + "-" +
                calendar.get(Calendar.MONTH) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(after);
        String afterString =
                calendar.get(Calendar.YEAR) + "-" +
                calendar.get(Calendar.MONTH) + "-" +
                calendar.get(Calendar.DAY_OF_MONTH);*/


        return Stream.of(
                // arguments(before, beforeString) // partition #1
                arguments(now, nowString) // partition #1
                // arguments(after, afterString) // partition #1
        );
    }
}
