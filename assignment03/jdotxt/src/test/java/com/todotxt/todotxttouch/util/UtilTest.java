package com.todotxt.todotxttouch.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class UtilTest {

    @ParameterizedTest
    @MethodSource("prependProvider")
    public void prepend(ArrayList<String> list, String prepend, ArrayList<String> expected) {
        Util.prependString(list, prepend);
        assertEquals(list, expected);
    }

    static Stream<Arguments> prependProvider() {
        ArrayList<String> list1 = new ArrayList<>(
                Arrays.asList("a", "b")
        );
        ArrayList<String> expectedList1 = new ArrayList<>(
                Arrays.asList("ca", "cb")
        );

        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> expectedList2 = new ArrayList<>();

        ArrayList<String> list3 = new ArrayList<>(
                Arrays.asList("a", "b")
        );
        ArrayList<String> expectedList3 = new ArrayList<>(
                Arrays.asList("a", "b")
        );

        ArrayList<String> list4 = new ArrayList<>();
        ArrayList<String> expectedList4 = new ArrayList<>();


        return Stream.of(
                arguments(list1, "c", expectedList1),
                arguments(list2, "c", expectedList2),
                arguments(list3, "", expectedList3),
                arguments(list4, "", expectedList4)
        );
    }

}
