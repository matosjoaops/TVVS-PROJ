package com.todotxt.todotxttouch.util;

import static org.junit.Assert.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class StringsTest {

    @ParameterizedTest
    @MethodSource("stringIntStringProvider")
    public void insertPadded(String baseString, int position, String stringToInsert, String expectedResult) {

        String result = Strings.insertPadded(baseString, position, stringToInsert);

        assertEquals(result, expectedResult);
    }

    static Stream<Arguments> stringIntStringProvider() {
        return Stream.of(
                arguments("apple", 1, "pear", "a pear pple"), // partition #1
                arguments("test", 4, "", "test"), // partition #4
                arguments("", 0, "a", "a ") // partition #5
        );
    }

    @ParameterizedTest()
    @MethodSource("stringInvalidPositionStringProvider")
    public void insertPaddedNegativePosition(String s, int insertAt, String stringToInsert) {
        assertThrows(IndexOutOfBoundsException.class, () -> Strings.insertPadded(s, insertAt, stringToInsert));
    }

    static Stream<Arguments> stringInvalidPositionStringProvider() {
        return Stream.of(
                arguments("a", -1, "b"), // partition #2
                arguments("a", 5, "b"), // partition #3
                arguments("test", -1, "") // partition #6
        );
    }
}
