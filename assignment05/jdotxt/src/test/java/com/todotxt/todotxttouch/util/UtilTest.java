package com.todotxt.todotxttouch.util;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilTest {

    @ParameterizedTest
    @MethodSource("prependProvider")
    public void prepend(ArrayList<String> list, String prepend, ArrayList<String> expected) {
        Util.prependString(list, prepend);
        assertEquals(list, expected);
    }

    @Test
    public void nullList() {
        assertThrows(Exception.class, () -> Util.prependString(null, "a"));//On-point between partitions 1 and 2 for list parameter
    }

    @Test
    public void nullString() {
        assertThrows(Exception.class, () -> Util.prependString(new ArrayList<>(Arrays.asList("b")), null));//On-point between partitions 1 and 2 for prepend parameter
    }

    static Stream<Arguments> prependProvider() {
        String normalString = "a";
        ArrayList<String> normalList1 = new ArrayList<>(Arrays.asList("b"));
        ArrayList<String> normalList2 = new ArrayList<>(Arrays.asList("b"));

        ArrayList<String> testList1 = new ArrayList<>();
        ArrayList<String> testList2 = new ArrayList<>(Arrays.asList("b"));

        String testString1 = "";
        String testString2 = "a";

        ArrayList<String> expectedList1 = new ArrayList<>();
        ArrayList<String> expectedList2 = new ArrayList<>(Arrays.asList("ab"));
        ArrayList<String> expectedList3 = new ArrayList<>(Arrays.asList("b"));
        ArrayList<String> expectedList4 = new ArrayList<>(Arrays.asList("ab"));

        return Stream.of(
                Arguments.of(testList1, normalString, expectedList1), //On-point between partitions 2 and 3 for list parameter
                                                                   //Off-point between partitions 1 and 2 for list parameter
                Arguments.of(testList2, normalString, expectedList2), //Off-point between partitions 2 and 3 for list parameter
                Arguments.of(normalList1, testString1, expectedList3),//On-point between partitions 2 and 3 for prepend parameter
                                                                   //Off-point between partitions 1 and 2 for prepend parameter
                Arguments.of(normalList2, testString2, expectedList4) //Off-point between partitions 2 and 3 for prepend parameter
        );
    }

}
