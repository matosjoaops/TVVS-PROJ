package com.todotxt.todotxttouch.util;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
                Arguments.of("/home/user/file1.txt", "file1.txt"),
                Arguments.of("dir/file2.txt", "file2.txt"),
                Arguments.of("file3.txt", "file3.txt"),
                Arguments.of("/home/user/\"space dir\"/file4.txt", "file4.txt"),
                Arguments.of("/home/user/space\\ dir/file5.txt", "file5.txt"),
                Arguments.of("", ""),
                Arguments.of("/test/path/", "path"),
                Arguments.of("/test/file", "file")
        );
    }

    @Test
    public void pathTest() {
        assertEquals(Path.fileName("./test/fileName/"), "fileName");
        assertEquals(Path.parentPath("./parentPath/fileName/"), "./parentPath/");
        assertEquals(Path.parentPath("  "), "");
        assertEquals(Path.parentPath("/"), "");
    }
}
