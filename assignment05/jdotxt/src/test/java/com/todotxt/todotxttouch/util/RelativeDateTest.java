package com.todotxt.todotxttouch.util;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class RelativeDateTest {

    @ParameterizedTest
    @MethodSource("dateProvider")
    public void getRelativeDate(Date date, String expectedResult) {

        String result = RelativeDate.getRelativeDate(date);

        assertEquals(result, expectedResult);
    }

    @Test(expected = NullPointerException.class)
    public void getRelativeDateNull() {

        RelativeDate.getRelativeDate((Date) null); // Partition #4;
                                                   // Between Partition #4 and Partition #1 On-point;
                                                   // Between Partition #4 and Partition #2 On-point;
                                                   // Between Partition #4 and Partition #3 On-point;
    }

    static Stream<Arguments> dateProvider() throws ParseException {
        Date now = new Date();
        Date before = new SimpleDateFormat("yyyy/MM/dd").parse("2000/10/1");
        Date after = new SimpleDateFormat("yyyy/MM/dd").parse("3000/10/1");

        Instant nowInstant = Instant.now();
        Instant before1DayInstant = nowInstant.minus(Duration.ofDays(1));
        Date before1Day = Date.from(before1DayInstant);

        Instant after1DayInstant = nowInstant.plus(Duration.ofDays(1));
        Date after1Day = Date.from(after1DayInstant);

        String nowString = getRelativeDateUtil(now, now);
        String beforeString = getRelativeDateUtil(now, before);
        String afterString = getRelativeDateUtil(now, after);
        String before1DayString = getRelativeDateUtil(now, before1Day);
        String after1DayString = getRelativeDateUtil(now, after1Day);

        return Stream.of(
                Arguments.of(before, beforeString), // Partition #1; Between Partition #4 and Partition #1 Off-point
                Arguments.of(now, nowString), // Partition #2; Between Partition #1 and Partition #2 Off-point;
                                           // Between Partition #2 and Partition #3 On-point;
                                           // Between Partition #4 and Partition #2 Off-point
                Arguments.of(after, afterString), // Partition #3; Between Partition #4 and Partition #3 Off-point
                Arguments.of(before1Day, before1DayString), // Between Partition #1 and Partition #2
                Arguments.of(after1Day, after1DayString) // Between Partition #2 and Partition #3
        );
    }

    static String getRelativeDateUtil(Date now, Date date) {
        Calendar calendar = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();

        calendar.setTime(now);
        calendar2.setTime(date);

        return String.format("%04d/%02d/%02d",
            calendar2.get(Calendar.YEAR) - calendar.get(Calendar.YEAR),
            calendar2.get(Calendar.MONTH) - calendar.get(Calendar.MONTH), // months start at 0
            calendar2.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH)
        );
    }
}
