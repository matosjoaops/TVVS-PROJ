package com.todotxt.todotxttouch.task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriorityTest {

    @Test
    public void range() {
        List<Priority> expected = new ArrayList<>(Arrays.asList(Priority.A, Priority.B, Priority.C));
        List<Priority> result = Priority.range(Priority.A, Priority.C);
        assertEquals(expected, result);
    }

    @Test
    public void rangeInCode() {
        List<String> expected = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<String> result = Priority.rangeInCode(Priority.A, Priority.C);
        assertEquals(expected, result);
    }

    @Test
    public void inCode() {
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("A", "B", "C"));
        List<Priority> priorities = new ArrayList<>(Arrays.asList(Priority.A, Priority.B, Priority.C));
        ArrayList<String> result = Priority.inCode(priorities);
        assertEquals(expected, result);
    }

    @Test
    public void toPriority() {
        ArrayList<Priority> expected = new ArrayList<>(Arrays.asList(Priority.A, Priority.D));
        List<String> codes = new ArrayList<>(Arrays.asList("A", "D"));
        ArrayList<Priority> result = Priority.toPriority(codes);
        assertEquals(result, expected);
    }
}
