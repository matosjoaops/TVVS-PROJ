package com.todotxt.todotxttouch.util;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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

    @ParameterizedTest
    @MethodSource("calendarProvider")
    public void getRelativeDateCalendar(Calendar date1, Calendar date2, String expectedResult) {
        String result = RelativeDate.getRelativeDate(date1, date2);
        assertEquals(expectedResult, result);
    }

    @Test(expected = NullPointerException.class)
    public void getRelativeDateNull() {

        RelativeDate date = new RelativeDate();

        RelativeDate.getRelativeDate((Date) null); // Partition #4;
                                                   // Between Partition #4 and Partition #1 On-point;
                                                   // Between Partition #4 and Partition #2 On-point;
                                                   // Between Partition #4 and Partition #3 On-point;
    }

    static Stream<Arguments> calendarProvider() throws ParseException {
        Calendar today = new GregorianCalendar();
        today.setTime(new Date());

        Calendar twoMonthsInTheFuture = new GregorianCalendar();
        LocalDate twoMonthsInTheFutureDate = LocalDate.now().plusMonths(2).plusDays(1);
        twoMonthsInTheFuture.setTime(Date.from(twoMonthsInTheFutureDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Calendar oneMonthInTheFuture = new GregorianCalendar();
        LocalDate oneMonthInTheFutureDate = LocalDate.now().plusMonths(1).plusDays(1);
        oneMonthInTheFuture.setTime(Date.from(oneMonthInTheFutureDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Calendar twoDaysInTheFuture = new GregorianCalendar();
        LocalDate twoDaysInTheFutureDate = LocalDate.now().plusDays(3);
        twoDaysInTheFuture.setTime(Date.from(twoDaysInTheFutureDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        Calendar oneDayInTheFuture = new GregorianCalendar();
        LocalDate oneDayInTheFutureDate = LocalDate.now().plusDays(2);
        oneDayInTheFuture.setTime(Date.from(oneDayInTheFutureDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return Stream.of(
                Arguments.of(twoMonthsInTheFuture, today, "2 months ago"),
                Arguments.of(oneMonthInTheFuture, today, "1 month ago"),
                Arguments.of(twoDaysInTheFuture, today, "2 days ago"),
                Arguments.of(oneDayInTheFuture, today, "1 day ago")
        );
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
