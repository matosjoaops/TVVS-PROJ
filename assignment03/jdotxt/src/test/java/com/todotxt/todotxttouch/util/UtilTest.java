package com.todotxt.todotxttouch.util;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class UtilTest {

    @ParameterizedTest
    @MethodSource("prependProvider")
    public void prepend(ArrayList<String> list, String prepend, ArrayList<String> expected) {
        Util.prependString(list, prepend);
        assertEquals(list, expected);
    }

    @Test
    public void nullList() {
        assertThrows(Exception.class, () -> Util.prependString(null, "a"));
    }

    @Test
    public void nullString() {
        assertThrows(Exception.class, () -> Util.prependString(new ArrayList<>(Arrays.asList("b")), null));
    }

    static Stream<Arguments> prependProvider() {
        String normalString = "a";
        ArrayList<String> normalList1 = new ArrayList<>(Arrays.asList("b"));
        ArrayList<String> normalList2 = new ArrayList<>(Arrays.asList("b"));

        ArrayList<String> testList2 = new ArrayList<>();
        ArrayList<String> testList3 = new ArrayList<>(Arrays.asList("b"));

        String testString1 = "";
        String testString2 = "a";

        ArrayList<String> expectedList1 = new ArrayList<>();
        ArrayList<String> expectedList2 = new ArrayList<>(Arrays.asList("ab"));
        ArrayList<String> expectedList3 = new ArrayList<>(Arrays.asList("b"));
        ArrayList<String> expectedList4 = new ArrayList<>(Arrays.asList("ab"));

        return Stream.of(
                arguments(testList2, normalString, expectedList1),
                arguments(testList3, normalString, expectedList2),
                arguments(normalList1, testString1, expectedList3),
                arguments(normalList2, testString2, expectedList4)
        );
    }

}
