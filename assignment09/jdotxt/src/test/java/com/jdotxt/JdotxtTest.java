package com.jdotxt;

import com.chschmid.jdotxt.Jdotxt;
import com.chschmid.jdotxt.util.FileModifiedListener;
import com.todotxt.todotxttouch.task.Task;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JdotxtTest {
    private static final String DEFAULTDIR = System.getProperty("user.home") + File.separator + "jdotxt";
    private static final String TODOPATH = DEFAULTDIR + File.separator + "todo.txt";

    @Test(expected = AssertionError.class)
    public void jdotxtTest() {
        Jdotxt.main(null);

        File todoFile = new File(TODOPATH);
        assertFalse(todoFile.exists());

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "A new task"));
        Jdotxt.taskBag.store(tasks);

        Jdotxt.storeTodos();
        FileModifiedListener fileModifiedListener = () -> {

        };
        Jdotxt.removeFileModifiedListener(fileModifiedListener);
        Jdotxt.archiveTodos();

        // what to test here???

        String newString = Jdotxt.insertReplaceString("123", "-", 0);

        assertEquals(newString, "-23");
    }

    @Test
    public void insertReplaceString1() {
        String original = "aaaaa";
        String replace = "bbb";
        int offset = 1;
        String expected = "abbba";
        String result = Jdotxt.insertReplaceString(original, replace, offset);
        assertEquals(expected, result);
    }

    @Test
    public void insertReplaceString2() {
        String original = "aaaaa";
        String replace = "bbbbb";
        int offset = 1;
        String expected = "abbbbb";
        String result = Jdotxt.insertReplaceString(original, replace, offset);
        assertEquals(expected, result);
    }

    @Test
    public void onWindows() {
        Jdotxt.onWindows();
    }
}
