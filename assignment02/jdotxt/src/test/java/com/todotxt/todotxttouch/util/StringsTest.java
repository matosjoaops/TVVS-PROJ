package com.todotxt.todotxttouch.util;

import static org.junit.Assert.assertEquals;

public class StringsTest {

    // TODO: use junit5 to use @ParameterizedTest

    public void insertPadded() {

        String baseString = "abc";
        int position = 3;
        String stringToInsert = "xxx";

        String result = Strings.insertPadded(baseString, position, stringToInsert);

        assertEquals(result, "abc xxx ");
    }
}
