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
    public void toPriority1() {
        ArrayList<Priority> expected = new ArrayList<>(Arrays.asList(Priority.A, Priority.D));
        List<String> codes = new ArrayList<>(Arrays.asList("A", "D"));
        ArrayList<Priority> result = Priority.toPriority(codes);
        assertEquals(result, expected);
    }

    @Test
    public void toPriority2() {
        Priority expected = Priority.NONE;
        Priority result = Priority.toPriority((String) null);
        assertEquals(result, expected);
    }

    @Test
    public void reverseValues() {
        Priority[] expected = {
                Priority.NONE,
                Priority.Z,
                Priority.Y,
                Priority.X,
                Priority.W,
                Priority.V,
                Priority.U,
                Priority.T,
                Priority.S,
                Priority.R,
                Priority.Q,
                Priority.P,
                Priority.O,
                Priority.N,
                Priority.M,
                Priority.L,
                Priority.K,
                Priority.J,
                Priority.I,
                Priority.H,
                Priority.G,
                Priority.F,
                Priority.E,
                Priority.D,
                Priority.C,
                Priority.B,
                Priority.A
        };
        assertEquals(expected, Priority.reverseValues());
    }

    @Test
    public void formats() {
        assertEquals("A", Priority.A.inListFormat());
        assertEquals("A", Priority.A.inDetailFormat());
    }
}
