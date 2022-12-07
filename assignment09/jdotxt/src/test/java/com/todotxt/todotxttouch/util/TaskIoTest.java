package com.todotxt.todotxttouch.util;

import com.todotxt.todotxttouch.task.Task;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskIoTest {
    private static final String DEFAULTDIR = System.getProperty("user.home") + File.separator + "jdotxt";
    private static final String TODOPATH = DEFAULTDIR + File.separator + "todo.txt";

    @Test
    public void loadTasksFromNonExistingFile() throws IOException {
        File nonExistingFile = new File("./non_existing_file");
        assertFalse(nonExistingFile.exists());
        TaskIo.loadTasksFromFile(nonExistingFile);
    }

    @Test
    public void writeToFileTest() throws IOException {
        File todoFile = new File(TODOPATH);

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "A new task"));

        TaskIo.writeToFile(tasks, todoFile);
        String fileContent = Files.readString(Path.of(TODOPATH), StandardCharsets.UTF_8);
        assertEquals(fileContent, "A new task\n");
    }

    @Test
    public void readLineTest() throws IOException {
        InputStream stream = new ByteArrayInputStream("test\r another test\n test".getBytes(StandardCharsets.UTF_8));

        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));

        String str1 = TaskIo.readLine(buffer);
        String str2 = TaskIo.readLine(buffer);
        String str3 = TaskIo.readLine(buffer);
        String str4 = TaskIo.readLine(buffer);
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        assertEquals(str1, "test\r");
        assertEquals(str2, " another test\n");
        assertEquals(str3, " test");
        assertNull(str4);
    }
}
