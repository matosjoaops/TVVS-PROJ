package com.todotxt.todotxttouch.util;

import com.todotxt.todotxttouch.task.Task;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskIoTest {
    private static final String DEFAULTDIR = System.getProperty("user.home") + File.separator + "jdotxt";
    private static final String TODOPATH = DEFAULTDIR + File.separator + "todo.txt";
    private static final String DONEPATH = DEFAULTDIR + File.separator + "done.txt";

    @Test
    public void loadTasksFromNonExistingFile() throws IOException {
        File nonExistingFile = new File("./non_existing_file");
        assertFalse(nonExistingFile.exists());
        TaskIo.loadTasksFromFile(nonExistingFile);
    }

    @Test
    public void writeToFileTest() throws IOException {
        File todoFile = new File(TODOPATH);
        assertTrue(todoFile.exists());

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "A new task"));

        TaskIo.writeToFile(tasks, todoFile);
        String fileContent = Files.readString(Path.of(TODOPATH), StandardCharsets.UTF_8);
        assertEquals(fileContent, "A new task\n");
    }
}
