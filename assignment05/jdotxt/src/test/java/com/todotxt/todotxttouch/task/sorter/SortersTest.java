package com.todotxt.todotxttouch.task.sorter;

import com.todotxt.todotxttouch.task.Priority;
import com.todotxt.todotxttouch.task.Task;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    @Test
    public void sortersByPriorityTest() {
        Task task1 = new Task(1, "task1");
        Task task2 = new Task(2, "task2");

        task1.setPriority(Priority.A);
        task2.setPriority(Priority.B);

        assertEquals(Sorters.PRIORITY.get(true).compare(task1, task2), -1);
        assertEquals(Sorters.PRIORITY.get(true).compare(task2, task1), 1);

        assertEquals(Sorters.PRIORITY.get(false).compare(task1, task2), 1);
        assertEquals(Sorters.PRIORITY.get(false).compare(task2, task1), -1);

        task2.setPriority(Priority.A);
        assertEquals(Sorters.PRIORITY.get(true).compare(task1, task2), 0);

        task2.setPriority(Priority.B);
        task1.markComplete(new Date());
        task2.markComplete(new Date());
        assertEquals(Sorters.PRIORITY.get(true).compare(task1, task2), 0);

        task1.markIncomplete();
        assertEquals(Sorters.PRIORITY.get(true).compare(task1, task2), -1);

        task1.markComplete(new Date());
        task2.markIncomplete();
        assertEquals(Sorters.PRIORITY.get(true).compare(task1, task2), 1);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByPriorityNullTaskTest1() {
        Task task1 = new Task(1, "task1");

        Sorters.PRIORITY.get(true).compare(null, task1);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByPriorityNullTaskTest2() {
        Task task1 = new Task(1, "task1");

        Sorters.PRIORITY.get(true).compare(task1, null);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByPriorityNullTaskTest3() {
        Sorters.PRIORITY.get(true).compare(null, null);
    }

    @Test
    public void sortersByTaskTextTest() {
        Task task1 = new Task(1, "task1");
        Task task2 = new Task(2, "task2");

        assertEquals(Sorters.TEXT.get(true).compare(task1, task2), -1);
        assertEquals(Sorters.TEXT.get(true).compare(task2, task1), 1);

        assertEquals(Sorters.TEXT.get(false).compare(task1, task2), 1);
        assertEquals(Sorters.TEXT.get(false).compare(task2, task1), -1);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByTextNullTaskTest1() {
        Task task1 = new Task(1, "task1");

        Sorters.TEXT.get(true).compare(null, task1);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByTextNullTaskTest2() {
        Task task1 = new Task(1, "task1");

        Sorters.TEXT.get(true).compare(task1, null);
    }

    @Test(expected = NullPointerException.class)
    public void sortersByTextNullTaskTest3() {
        Sorters.TEXT.get(true).compare(null, null);
    }

    @Test
    public void sortersByCreationDateTaskTest() {
        Task task1 = new Task(1, "task1");
        Task task2 = new Task(2, "task2");

        assertEquals(Sorters.DATE.get(true).compare(task1, task2), 0);
        assertEquals(Sorters.DATE.get(false).compare(task1, task2), 0);
    }

    @Test
    public void sortersByCompletionDateTaskTest() {
        Task task1 = new Task(1, "task1");
        Task task2 = new Task(2, "task2");

        task1.markComplete(new Date());
        task2.markComplete(new Date());

        assertEquals(Sorters.COMPLETION_DATE.get(true).compare(task1, task2), 0);
        assertEquals(Sorters.COMPLETION_DATE.get(false).compare(task1, task2), 0);
    }
}
