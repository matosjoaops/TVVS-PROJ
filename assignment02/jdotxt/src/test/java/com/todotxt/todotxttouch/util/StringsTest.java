package com.todotxt.todotxttouch.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringsTest {

    @Test
    public void insertPadded() {

        String baseString = "string";
        int position = 3;
        String stringToInsert = "insert";

        String result = Strings.insertPadded(baseString, position, stringToInsert);

        assertEquals(result, "str insert ing");
    }
}
