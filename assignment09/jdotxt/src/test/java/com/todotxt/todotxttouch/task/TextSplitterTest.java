package com.todotxt.todotxttouch.task;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextSplitterTest {

    @Test
    public void split1() {
        Task task = new Task(1, "x Some text");
        assertTrue(task.isCompleted());
    }

    @Test(expected = NullPointerException.class)
    public void split2() {
        new Task(1, null);
    }

    @Test
    public void split3() {
        String dateString = "1970-01-01";
        Task task = new Task(1,"x " + dateString + " 1970-01-02");
        assertEquals(task.getPrependedDate(), "");
    }

    @Test
    public void split4() {
        String dateString = "1970-01-01";
        Task task = new Task(1,"x " + dateString + " 1970-01-01 some text");
        assertEquals(task.getPrependedDate(), dateString);
    }
}
