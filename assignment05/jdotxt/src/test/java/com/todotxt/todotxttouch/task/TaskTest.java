package com.todotxt.todotxttouch.task;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private final long testId1 = 1;
    private final long testId2 = 2;

    private final String testText1 = "Some text";
    private final String testText2 = "Some other text";

    private final Date testDate = new Date(0);

    @Test
    public void createTask() {
        Task task = new Task(testId1, testText1);
        long taskId = task.getId();
        String taskText = task.getText();

        assertEquals(taskId, 1);
        assertEquals(taskText, "Some text");
    }

    @Test
    public void markComplete() {
        Task task = new Task(testId1, testText1);
        assertFalse(task.isCompleted());
        task.markComplete(new Date());
        assertTrue(task.isCompleted());
    }

    @Test
    public void markIncomplete() {
        Task task = new Task(testId1, testText1);
        task.markComplete(new Date());
        assertTrue(task.isCompleted());
        task.markIncomplete();
        assertFalse(task.isCompleted());
    }

    @Test
    public void compareTask1() {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId2, testText2);
        assertNotEquals(task1, task2);
    }

    @Test
    public void compareTask2() {
        Task task1 = new Task(testId1, testText1);
        assertNotEquals(task1, null);
    }

    @Test
    public void compareTask3() {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);
        assertEquals(task1, task2);
    }

    @Test
    public void compareTask4() {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        task1.markComplete(testDate);

        assertNotEquals(task1, task2);
    }

    @Test
    public void compareTask5() {
        Task task1 = new Task(testId1, testText1, testDate);
        Task task2 = new Task(testId1, testText1, new Date());
        assertNotEquals(task1, task2);
    }


    @Test
    public void hash() {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId2, testText2);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void date() {
        Task task = new Task(testId1, testText1, testDate);
        String taskDate = task.getPrependedDate();

        assertEquals(taskDate, "1970-01-01");
    }

    @Test
    public void priority() {
        Task task = new Task(testId1, testText1);
        task.setPriority(Priority.A);
        Priority taskPriority = task.getPriority();

        assertEquals(taskPriority, Priority.A);
    }

    @Test
    public void updateText() {
        Task task = new Task(testId1, testText1);

        task.update(testText2);

        assertEquals(task.getText(), testText2);
        assertEquals(task.getOriginalText(), testText1);
    }

    @Test
    public void delete() {
        Task task = new Task(testId1, testText1);
        assertFalse(task.isDeleted());
        task.delete();
        assertTrue(task.isDeleted());
    }

    @Test
    public void completionDate() {
        Task task = new Task(testId1, testText1);
        task.markComplete(testDate);
        assertEquals(task.getCompletionDate(), "1970-01-01");
    }
}
