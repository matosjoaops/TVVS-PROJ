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


    @ParameterizedTest
    @MethodSource("stringProvider")
    public void isBlank(String s, boolean expectedResult) {

        boolean result = Strings.isBlank(s);

        assertEquals(result, expectedResult);
    }

    static Stream<Arguments> stringProvider() {
    return Stream.of(
        arguments(null, true), // Partition #1; Between Partition #1 and Partition #2 On-point
        arguments("", true), // Partition #2; Between Partition #1 and Partition #2 Off-point; 
                             // Between Partition #2 and Partition #3 On-point;
                             // Between Partition #2 and Partition #4 On-point
        arguments("   ", true), // Partition #3
        arguments("123", false), // Partition #4
        arguments(" ", true), // Between Partition #2 and Partition #3 Off-point
        arguments("a", false) // Between Partition #2 and Partition #4 Off-point
    );
}

}
