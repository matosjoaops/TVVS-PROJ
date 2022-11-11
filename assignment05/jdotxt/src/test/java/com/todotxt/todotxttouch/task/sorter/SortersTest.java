package com.todotxt.todotxttouch.task.sorter;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class SortersTest {

    @Test
    public void sortersTest() throws ParseException {
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();

        calendar1.setTime(new SimpleDateFormat("yyyy/MM/dd").parse("2000/10/1"));
        calendar2.setTime(new SimpleDateFormat("yyyy/MM/dd").parse("2000/10/2"));

        assertEquals(Sorters.compareDates(calendar1, calendar2, true), -1);
        assertEquals(Sorters.compareDates(calendar1, calendar2, false), 1);
        assertEquals(Sorters.compareDates(calendar1, calendar1, true), 0);
        assertEquals(Sorters.compareDates(null, null, true), 0);
        assertEquals(Sorters.compareDates(calendar1, null, true), -1);
        assertEquals(Sorters.compareDates(null, calendar1, true), 1);
    }
}
