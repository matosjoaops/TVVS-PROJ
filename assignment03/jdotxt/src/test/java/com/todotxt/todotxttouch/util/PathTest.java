package com.todotxt.todotxttouch.util;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PathTest {

    @ParameterizedTest
    @MethodSource("pathProvider")
    public void fileName(String path, String expected) {
        String result = Path.fileName(path);
        assertEquals(result, expected);
    }

    @Test
    public void invalidFileName() {
        assertThrows(Exception.class, () -> Path.fileName("/home/user/space dir/file6.txt"));
    }

    static Stream<Arguments> pathProvider() {
        return Stream.of(
                arguments("/home/user/file1.txt", "file1.txt"),
                arguments("dir/file2.txt", "file2.txt"),
                arguments("file3.txt", "file3.txt"),
                arguments("/home/user/\"space dir\"/file4.txt", "file4.txt"),
                arguments("/home/user/space\\ dir/file5.txt", "file5.txt"),
                arguments("", "")
        );
    }
}
