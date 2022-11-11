package com.jdotxt;

import com.chschmid.jdotxt.Jdotxt;
import com.chschmid.jdotxt.util.FileModifiedListener;
import com.todotxt.todotxttouch.task.Task;
import com.todotxt.todotxttouch.util.TaskIo;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JdotxtTest {
    private static final String DEFAULTDIR = System.getProperty("user.home") + File.separator + "jdotxt";
    private static final String TODOPATH = DEFAULTDIR + File.separator + "todo.txt";
    private static final String DONEPATH = DEFAULTDIR + File.separator + "done.txt";

    @Test
    public void jdotxtTest() {
        Jdotxt.main(null);

        File todoFile = new File(TODOPATH);
        assertTrue(todoFile.exists());

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
}
