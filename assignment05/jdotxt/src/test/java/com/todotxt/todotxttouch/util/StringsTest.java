package com.todotxt.todotxttouch.util;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class StringsTest {

    @ParameterizedTest
    @MethodSource("stringIntStringProvider2")
    public void insertPaddedIfNeeded(String baseString, int position, String stringToInsert, String expectedResult) {

        String result = Strings.insertPaddedIfNeeded(baseString, position, stringToInsert);

        assertEquals(result, expectedResult);
    }

    static Stream<Arguments> stringIntStringProvider2() {
        return Stream.of(
                Arguments.of("A ", 1, "pear", "A pear"),
                Arguments.of("A", 1, "pear", "A pear"),
                Arguments.of("A ", 1, " pear", "A pear"),
                Arguments.of("A string", 7,  "string", "A string string"),
                Arguments.of("A", 1,  "", "A")
        );
    }

    @ParameterizedTest
    @MethodSource("stringIntStringProvider")
    public void insertPadded(String baseString, int position, String stringToInsert, String expectedResult) {

        String result = Strings.insertPadded(baseString, position, stringToInsert);

        assertEquals(result, expectedResult);
    }

    static Stream<Arguments> stringIntStringProvider() {
        return Stream.of(
                Arguments.of("apple", 1, "pear", "a pear pple"), // partition #1
                Arguments.of("test", 4, "", "test"), // partition #4
                Arguments.of("", 0, "a", "a ") // partition #5
        );
    }

    @ParameterizedTest()
    @MethodSource("stringInvalidPositionStringProvider")
    public void insertPaddedNegativePosition(String s, int insertAt, String stringToInsert) {
        assertThrows(IndexOutOfBoundsException.class, () -> Strings.insertPadded(s, insertAt, stringToInsert));
    }

    static Stream<Arguments> stringInvalidPositionStringProvider() {
        return Stream.of(
                Arguments.of("a", -1, "b"), // partition #2
                Arguments.of("a", 5, "b"), // partition #3
                Arguments.of("test", -1, "") // partition #6
        );
    }


    @ParameterizedTest
    @MethodSource("stringProvider")
    public void isBlank(String s, boolean expectedResult) {

        boolean result = Strings.isBlank(s);

        assertEquals(result, expectedResult);
    }

    static Stream<Arguments> stringProvider() {
    return Stream.of(
            Arguments.of(null, true), // Partition #1; Between Partition #1 and Partition #2 On-point
            Arguments.of("", true), // Partition #2; Between Partition #1 and Partition #2 Off-point;
                             // Between Partition #2 and Partition #3 On-point;
                             // Between Partition #2 and Partition #4 On-point
            Arguments.of("   ", true), // Partition #3
            Arguments.of("123", false), // Partition #4
            Arguments.of(" ", true), // Between Partition #2 and Partition #3 Off-point
            Arguments.of("a", false) // Between Partition #2 and Partition #4 Off-point
        );
    }

}
