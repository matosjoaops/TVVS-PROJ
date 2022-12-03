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

        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        Instant nowInstant = Instant.now();

        Instant after1DayInstant = nowInstant.plus(Duration.ofDays(1));
        Date after1Day = Date.from(after1DayInstant);

        String expectedAfter1Day = formatter.format(after1Day);
        String expectedNow = formatter.format(now);

        return Stream.of(
                Arguments.of(before, "2000-10-01"),
                Arguments.of(now, expectedNow),
                Arguments.of(after, "3000-10-01"),
                Arguments.of(after1Day, expectedAfter1Day),
                Arguments.of(new Date(), expectedNow)
        );
    }
}
