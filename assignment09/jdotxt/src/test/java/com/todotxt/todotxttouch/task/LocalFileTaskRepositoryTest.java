package com.todotxt.todotxttouch.task;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LocalFileTaskRepositoryTest {

    private static final String DEFAULTDIR = System.getProperty("user.home") + File.separator + "jdotxt";
    public static final String TODOPATH = DEFAULTDIR + File.separator + "todo.txt";
    public static final String DONEPATH = DEFAULTDIR + File.separator + "done.txt";

    private boolean findStringInFile(String string, File file) {
        try {
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            String fileContent = new String(fileBytes);
            return fileContent.contains(string);
        }
        catch (IOException e) {
            return false;
        }
    }

    @Test
    public void archive() {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();

        String doneString = "Done task";
        String todoString = "Todo task";

        Task task1 = new Task(1, doneString);
        Task task2 = new Task(2, todoString);

        task1.markComplete(new Date());
        ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(task1, task2));

        repository.archive(taskList);
        File doneFile = new File(DONEPATH);
        File todoFile = new File(TODOPATH);

        assertTrue(findStringInFile(doneString, doneFile));
        assertTrue(findStringInFile(todoString, todoFile));
    }

    @Test()
    public void load1() {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();

        File file = new File(TODOPATH);
        file.delete();

        repository.load();

        assertTrue(file.exists());
    }

    @Test
    public void load2() throws IOException {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();

        FileWriter fileWriter = new FileWriter(TODOPATH, false);
        fileWriter.write("afdaf");

        ArrayList<Task> tasks = repository.load();
        assertEquals(tasks.size(), 0);
    }

    @Test()
    public void loadDoneTasks1() {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();

        File file = new File(DONEPATH);
        file.delete();

        repository.loadDoneTasks();

        assertFalse(file.exists());
    }

    @Test
    public void loadDoneTasks2() throws IOException {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();

        FileWriter fileWriter = new FileWriter(DONEPATH, false);
        fileWriter.write("afdaf");

        ArrayList<Task> tasks = repository.loadDoneTasks();
        assertEquals(tasks.size(), 0);
    }

    @Test
    public void doneFileModifiedSince() throws IOException {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();

        FileWriter fileWriter = new FileWriter(DONEPATH, true);
        fileWriter.write("afdaf");

        boolean result = repository.doneFileModifiedSince(new Date(0));
        assertTrue(result);
    }

    @Test
    public void todoFileModifiedSince() throws IOException {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();

        FileWriter fileWriter = new FileWriter(TODOPATH, true);
        fileWriter.write("afdaf");

        boolean result = repository.todoFileModifiedSince(new Date(0));
        assertTrue(result);
    }

    @Test
    public void initFiles() {
        File file = new File(TODOPATH);
        file.delete();
        LocalFileTaskRepository.initFiles();
    }

    @Test
    public void purge() {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();
        repository.purge();
        File todoFile = new File(TODOPATH);
        File doneFile = new File(DONEPATH);
        assertFalse(todoFile.exists());
        assertFalse(doneFile.exists());
    }

    @Test
    public void storeDoneTasks() {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();
        repository.storeDoneTasks(new File(TODOPATH));
        repository.storeDoneTasks(new ArrayList<>());
    }

    @Test
    public void store() {
        LocalFileTaskRepository repository = new LocalFileTaskRepository();
        repository.store(new ArrayList<>());
    }
}
