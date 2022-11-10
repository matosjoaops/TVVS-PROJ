package com.todotxt.todotxttouch.task;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalFileTaskRepositoryTest {

    private final String DEFAULTDIR = System.getProperty("user.home") + File.separator + "jdotxt";

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

    @BeforeEach
    private void clearDefaultDir() {
        try {
            File directory = new File(DEFAULTDIR);
            Files.walk(directory.toPath())
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {}
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
        File doneFile = new File(DEFAULTDIR + File.separator + "done.txt");
        File todoFile = new File(DEFAULTDIR + File.separator + "todo.txt");

        assertTrue(findStringInFile(doneString, doneFile));
        assertTrue(findStringInFile(todoString, todoFile));
    }
}
