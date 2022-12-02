package com.todotxt.todotxttouch.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CursorPositionCalculatorTest {
    @Test
    public void cursorPositionCalculatorTest() {
        assertEquals(CursorPositionCalculator.calculate(1, "A", "B"), 1);
        assertEquals(CursorPositionCalculator.calculate(-1, "AA", "B"), 0);
        assertEquals(CursorPositionCalculator.calculate(3, "AA", "B"), 1);
        assertEquals(CursorPositionCalculator.calculate(1, "A", null), 0);
        assertEquals(CursorPositionCalculator.calculate(1, null, "B"), 1);
    }
}
