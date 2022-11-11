package com.todotxt.todotxttouch.task.sorter;

import com.todotxt.todotxttouch.task.Task;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class SortersTest {

    @Test
    public void compareDatesTest() throws ParseException {
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

    @Test
    public void compareListsTest() {
        ArrayList<Integer> integerList1 = new ArrayList<>();
        integerList1.add(1);

        ArrayList<Integer> integerList2 = new ArrayList<>();
        integerList2.add(1);

        ArrayList<Integer> integerList3 = new ArrayList<>();
        integerList3.add(2);
        integerList3.add(3);

        ArrayList<Integer> integerList4 = new ArrayList<>();
        integerList4.add(2);
        integerList4.add(3);
        integerList4.add(4);

        assertEquals(Sorters.compareLists(integerList1, integerList1), 0);
        assertEquals(Sorters.compareLists(integerList1, integerList2), 0);
        assertEquals(Sorters.compareLists(integerList1, integerList3), -1);
        assertEquals(Sorters.compareLists(integerList3, integerList2), 1);
        assertEquals(Sorters.compareLists(integerList3, integerList4), -1);
    }

    @Test
    public void sortersByIDTest() {
        Task task1 = new Task(1, "task1");
        Task task2 = new Task(2, "task2");

        assertEquals(Sorters.ID.get(true).compare(task1, task2), -1);
        assertEquals(Sorters.ID.get(true).compare(task2, task1), 1);

        assertEquals(Sorters.ID.get(false).compare(task1, task2), 1);
        assertEquals(Sorters.ID.get(false).compare(task2, task1), -1);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByIDNullTaskTest1() {
        Task task1 = new Task(1, "task1");

        Sorters.ID.get(true).compare(null, task1);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByIDNullTaskTest2() {
        Task task1 = new Task(1, "task1");

        Sorters.ID.get(true).compare(task1, null);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByIDNullTaskTest3() {
        Sorters.ID.get(true).compare(null, null);
    }
}
