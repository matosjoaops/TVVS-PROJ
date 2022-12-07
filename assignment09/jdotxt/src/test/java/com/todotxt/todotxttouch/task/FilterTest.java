package com.todotxt.todotxttouch.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {

    @Test
    public void byPriorityFilter() {
        List<Priority> priorities = new ArrayList<>(Arrays.asList(Priority.A, Priority.B));
        List<Priority> emptyList = new ArrayList<>();

        ByPriorityFilter filter = new ByPriorityFilter(priorities);

        assertEquals(priorities, filter.getPriorities());
        assertNotEquals(emptyList, filter.getPriorities());
    }

    @Test
    public void byPriorityFilter2() {
        List<Priority> priorities = new ArrayList<>(Arrays.asList(Priority.A, Priority.B));
        List<Priority> emptyList = new ArrayList<>();

        Task task1 = new Task(1, "Some text");
        Task task2 = new Task(2, "Some text");
        task1.setPriority(Priority.A);

        ByPriorityFilter filter1 = new ByPriorityFilter(priorities);
        ByPriorityFilter filter2 = new ByPriorityFilter(emptyList);

        assertTrue(filter2.apply(task1));
        assertTrue(filter1.apply(task1));
        assertFalse(filter1.apply(task2));
    }

    @Test
    public void thresholdDateFilter() {
        ThresholdDateFilter filter = new ThresholdDateFilter();
        Task task1 = new Task(1, "Some text t:1970-01-01 some text");
        Task task2 = new Task(2, "Some text t:2050-01-01 some text");
        Task task3 = new Task(3, "Some text");
        assertTrue(filter.apply(task3));
        assertTrue(filter.apply(task1));
        assertFalse(filter.apply(task2));
    }

    @Test
    public void hiddenFilter() {
        HiddenFilter filter = new HiddenFilter();
        Task task1 = new Task(1, "Some text");
        Task task2 = new Task(2, "Some text h:1");
        assertTrue(filter.apply(task1));
        assertFalse(filter.apply(task2));
    }

    @Test
    public void byContextFilter() {
        List<String> contexts = new ArrayList<>(List.of("test"));
        List<String> contexts2 = new ArrayList<>(List.of("-"));
        List<String> emptyList = new ArrayList<>();

        Task task1 = new Task(1, "Some text @test some text");
        Task task2 = new Task(2, "Some text");

        ByContextFilter filter1 = new ByContextFilter(contexts);
        ByContextFilter filter2 = new ByContextFilter(emptyList);
        ByContextFilter filter3 = new ByContextFilter(contexts2);

        assertTrue(filter2.apply(task1));
        assertTrue(filter1.apply(task1));
        assertTrue(filter3.apply(task2));
        assertFalse(filter1.apply(task2));
    }

    @Test
    public void byContextFilter2() {
        List<String> contexts = new ArrayList<>(List.of("test"));
        List<String> emptyList = new ArrayList<>();

        ByContextFilter filter = new ByContextFilter(contexts);

        assertEquals(contexts, filter.getContexts());
        assertNotEquals(emptyList, filter.getContexts());
    }
}
