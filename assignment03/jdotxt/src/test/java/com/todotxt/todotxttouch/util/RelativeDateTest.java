package com.todotxt.todotxttouch.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        assertEquals(result, expectedResult);
    }

    static Stream<Arguments> dateProvider() throws ParseException {
        Calendar calendar = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();

        Date now = new Date();
        Date before = new SimpleDateFormat("yyyy/MM/dd").parse("2000/10/1");
        Date after = new SimpleDateFormat("yyyy/MM/dd").parse("3000/10/1");

        calendar.setTime(now);
        calendar2.setTime(now);
        String nowString = String.format("%04d/%02d/%02d",
            calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR),
            calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH), // months start at 0
            calendar2.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
        );


        calendar2.setTime(before);
        String beforeString = String.format("%04d/%02d/%02d",
            calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR),
            calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH), // months start at 0
            calendar2.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
        );

        calendar2.setTime(after);
        String afterString = String.format("%04d/%02d/%02d",
            calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR),
            calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH), // months start at 0
            calendar2.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
        );


        return Stream.of(
                arguments(before, beforeString), // partition #1
                arguments(now, nowString), // partition #2
                arguments(after, afterString) // partition #3
        );
    }
}
