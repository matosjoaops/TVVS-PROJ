package com.todotxt.todotxttouch.util;

import com.todotxt.todotxttouch.TodoException;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {

    private final String DEFAULTDIR = System.getProperty("user.home") + File.separator + "jdotxt";
    private final String TODOPATH = DEFAULTDIR + File.separator + "todo.txt";
    private final String DONEPATH = DEFAULTDIR + File.separator + "done.txt";

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

    @Test(expected = TodoException.class)
    public void copyNonExistingFileTest() {
        File origFile = new File("./origFileNonExistent.txt");
        File newFile = new File("./newFile.txt");

        Util.copyFile(origFile, newFile, false);
    }

    @Test
    public void copyExistingFileTest() {
        File origFile = new File(TODOPATH);
        File newFile = new File("./new_file.txt");

        assertTrue(origFile.exists());
        Util.copyFile(origFile, newFile, false);

        newFile.delete();
        assertFalse(newFile.exists());
    }

    @Test(expected = TodoException.class)
    public void renameNonExistingFileTest() {
        File origFile = new File("./nonExistingFile.txt");
        File newFile = new File("./newFile.txt");

        Util.renameFile(origFile, newFile, false);
    }

    @Test
    public void renameFileTest() throws IOException {
        File origFile = new File(DONEPATH);
        File newFile = new File("./newFile.txt");

        assertTrue(origFile.exists());
        Util.renameFile(origFile, newFile, false);

        new File(DONEPATH).createNewFile();
    }
}
