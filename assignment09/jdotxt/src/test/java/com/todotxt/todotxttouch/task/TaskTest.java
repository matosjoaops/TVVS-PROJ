package com.todotxt.todotxttouch.task;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    private final static long testId1 = 1;
    private final static long testId2 = 2;

    private final static String testText1 = "Some text";
    private final static String testText2 = "Some other text";

    private final static Date testDate = new Date(0);

    @Test
    public void createTask() {
        Task task = new Task();
        assertEquals(task.getId(), 0);
        assertEquals(task.getText(), "");
    }

    @Test
    public void rec() {
        Task task = new Task(testId1, "rec:+9d");
        assertTrue(task.isRec());
    }

    @Test
    public void rec2() {
        Task task = new Task(testId1, "rec:+9m");
        assertTrue(task.isRec());
    }

    @Test
    public void rec3() {
        Task task = new Task(testId1, "rec:+9y");
        assertTrue(task.isRec());
    }

    @Test
    public void hidden() {
        Task task = new Task(testId1, "h:1");
        assertTrue(task.isHidden());
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

    @ParameterizedTest
    @MethodSource("comparisonProvider")
    public void compareTask(Task task1, Object task2, boolean equal) {
        if (equal) assertEquals(task1, task2);
        else assertNotEquals(task1, task2);
    }

    private static Stream<Arguments> comparisonProvider() throws NoSuchFieldException, IllegalAccessException {
        Task simpleTask = new Task(testId1, testText1);

        Task completedTask = new Task(testId1, testText1);
        completedTask.markComplete(testDate);

        Task taskWithDate1 = new Task(testId1, testText1, testDate);
        Task taskWithDate2 = new Task(testId1, testText1, new Date(100000000));

        Task taskWithCompletionDate1 = new Task(testId1, testText1);
        Task taskWithCompletionDate2 = new Task(testId1, testText1);
        taskWithCompletionDate1.markComplete(new Date());
        taskWithCompletionDate2.markComplete(testDate);

        Task taskWithPriority = new Task(testId1, testText1);
        taskWithPriority.setPriority(Priority.A);

        Task taskWithEmail = new Task(testId1, "example@email.com");
        Task taskWithLink = new Task(testId1, "https://www.google.com");
        Task taskWithRec = new Task(testId1, "rec:+9w");
        Task taskWithAt = new Task(testId1, "Some text @test some text");
        Task taskWithPlus = new Task(testId1, "Some text +test some text");

        Task taskWithNullCompletionDate = new Task(testId1, testText1);
        Field completionDate = taskWithNullCompletionDate.getClass().getDeclaredField("completionDate");
        completionDate.setAccessible(true);
        completionDate.set(taskWithNullCompletionDate, null);

        Task taskWithNullContexts = new Task(testId1, testText1);
        Field contexts = taskWithNullContexts.getClass().getDeclaredField("contexts");
        contexts.setAccessible(true);
        contexts.set(taskWithNullContexts, null);

        Task taskWithNullLinks = new Task(testId1, testText1);
        Field links = taskWithNullLinks.getClass().getDeclaredField("links");
        links.setAccessible(true);
        links.set(taskWithNullLinks, null);

        Task taskWithNullMailAddresses = new Task(testId1, testText1);
        Field mailAddresses = taskWithNullMailAddresses.getClass().getDeclaredField("mailAddresses");
        mailAddresses.setAccessible(true);
        mailAddresses.set(taskWithNullMailAddresses, null);

        Task taskWithNullPhoneNumbers = new Task(testId1, testText1);
        Field phoneNumbers = taskWithNullPhoneNumbers.getClass().getDeclaredField("phoneNumbers");
        phoneNumbers.setAccessible(true);
        phoneNumbers.set(taskWithNullPhoneNumbers, null);

        Task taskWithNullDate = new Task(testId1, testText1);
        Field date = taskWithNullDate.getClass().getDeclaredField("prependedDate");
        date.setAccessible(true);
        date.set(taskWithNullDate, null);

        Task taskWithNullProjects = new Task(testId1, testText1);
        Field projects = taskWithNullProjects.getClass().getDeclaredField("projects");
        projects.setAccessible(true);
        projects.set(taskWithNullProjects, null);

        Task taskWithNullAge = new Task(testId1, testText1);
        Field age = taskWithNullAge.getClass().getDeclaredField("relativeAge");
        age.setAccessible(true);
        age.set(taskWithNullAge, null);

        Task taskWithNullText = new Task(testId1, testText1);
        Field text = taskWithNullText.getClass().getDeclaredField("text");
        text.setAccessible(true);
        text.set(taskWithNullText, null);

        Task deletedTask = new Task(testId1, testText1);
        deletedTask.delete();

        return Stream.of(
                Arguments.of(simpleTask, new Task(testId2, testText2), false),
                Arguments.of(simpleTask, null, false),
                Arguments.of(simpleTask, new Task(testId1, testText1), true),
                Arguments.of(completedTask, simpleTask, false),
                Arguments.of(taskWithDate1, taskWithDate2, false),
                Arguments.of(simpleTask, new Object(), false),
                Arguments.of(simpleTask, simpleTask, true),
                Arguments.of(taskWithCompletionDate1, taskWithCompletionDate2, false),
                Arguments.of(taskWithPriority, simpleTask, false),
                Arguments.of(simpleTask, new Task(testId1, testText2), false),
                Arguments.of(taskWithEmail, simpleTask, false),
                Arguments.of(taskWithLink, simpleTask, false),
                Arguments.of(taskWithRec, simpleTask, false),
                Arguments.of(taskWithAt, simpleTask, false),
                Arguments.of(taskWithPlus, simpleTask, false),
                Arguments.of(taskWithNullCompletionDate, simpleTask, false),
                Arguments.of(taskWithNullContexts, simpleTask, false),
                Arguments.of(taskWithNullLinks, simpleTask, false),
                Arguments.of(taskWithNullMailAddresses, simpleTask, false),
                Arguments.of(taskWithNullPhoneNumbers, taskWithEmail, false),
                Arguments.of(taskWithNullDate, simpleTask, false),
                Arguments.of(taskWithNullProjects, simpleTask, false),
                Arguments.of(taskWithNullAge, simpleTask, false),
                Arguments.of(taskWithNullText, simpleTask, false),
                Arguments.of(simpleTask, deletedTask, false),
                Arguments.of(simpleTask, taskWithNullAge, false),
                Arguments.of(taskWithEmail, taskWithNullPhoneNumbers, false)
        );
    }

    @Test
    public void hash1() {
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
    public void hash2() {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        task1.markComplete(new Date());

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash3() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("completionDate");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash4() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("contexts");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash5() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("links");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash6() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("mailAddresses");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash7() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        task1.delete();

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash8() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("phoneNumbers");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash9() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("prependedDate");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash10() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("priority");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void has11() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("projects");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash12() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("relativeAge");
        field.setAccessible(true);
        field.set(task2, null);

        String value1 = "A value";
        String value2 = "Another value";

        HashMap<Task, String> map = new HashMap<>();
        String putResult1 = map.put(task1, value1);
        assertNull(putResult1);

        String putResult2 = map.put(task2, value2);
        assertNull(putResult2);
    }

    @Test
    public void hash13() throws NoSuchFieldException, IllegalAccessException {
        Task task1 = new Task(testId1, testText1);
        Task task2 = new Task(testId1, testText1);

        Field field = task2.getClass().getDeclaredField("text");
        field.setAccessible(true);
        field.set(task2, null);

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

    @Test
    public void fileFormat1() {
        Task task = new Task(testId1, testText1);
        String fileFormat = task.inFileFormat();
        assertEquals(fileFormat, testText1);
    }

    @Test
    public void fileFormat2() {
        Task task = new Task(testId1, testText1);
        task.setPriority(Priority.A);
        String fileFormat = task.inFileFormat();
        assertEquals(fileFormat, "(A) " + testText1);
    }

    @Test
    public void fileFormat3() {
        Task task = new Task(testId1, testText1, testDate);
        String fileFormat = task.inFileFormat();
        assertEquals(fileFormat, "1970-01-01 " + testText1);
    }

    @Test
    public void fileFormat4() {
        Task task = new Task(testId1, testText1);
        task.markComplete(testDate);
        String fileFormat = task.inFileFormat();
        assertEquals(fileFormat, "x 1970-01-01 " + testText1);
    }

    @Test
    public void fileFormat5() {
        Task task = new Task(testId1, testText1);
        task.markComplete(testDate);
        String fileFormat = task.inFileFormatHeaderNoDate();
        assertEquals(fileFormat, "x 1970-01-01 ");
    }

    @Test
    public void fileFormat6() {
        Task task = new Task(testId1, testText1);
        task.setPriority(Priority.A);
        String fileFormat = task.inFileFormatHeaderNoDate();
        assertEquals(fileFormat, "(A) ");
    }

    @Test
    public void fileFormat7() {
        Task task = new Task(testId1, testText1, testDate);
        task.markComplete(testDate);
        String fileFormat = task.inFileFormatHeader();
        assertEquals(fileFormat, "x 1970-01-01 1970-01-01 ");
    }

    @Test
    public void screenFormat() {
        Task task = new Task(testId1, testText1, testDate);
        task.markComplete(testDate);
        String screenFormat = task.inScreenFormat();
        assertEquals(screenFormat, "x 1970-01-01 1970-01-01 " + testText1);
    }

    @Test
    public void initWithFilters() {
        Task task = new Task(testId1, testText1);
        ArrayList<Priority> priorities = new ArrayList<>(Arrays.asList(Priority.A));
        ArrayList<String> contexts = new ArrayList<>(Arrays.asList("testContext"));
        ArrayList<String> projects = new ArrayList<>(Arrays.asList("testProject"));
        task.initWithFilters(priorities, contexts, projects);
        assertEquals(task.getPriority(), Priority.A);
        assertEquals(task.getContexts(), contexts);
        assertEquals(task.getProjects(), projects);
    }
}
