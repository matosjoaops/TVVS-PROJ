package com.todotxt.todotxttouch.task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class TaskBagImplTest {

    LocalTaskRepository repository = mock(LocalTaskRepository.class);

    ArrayList<Task> tasks = new ArrayList<>(Arrays.asList(mock(Task.class)));

    @Test
    public void createBagImpl() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        assertNotNull(bagImpl);
    }

    @Test
    public void store() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.store(tasks);

        List<Task> bagImplTasks = bagImpl.getTasks();
        assertEquals(bagImplTasks, tasks);
    }

    @Test
    public void archive() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.archive();
        bagImpl.unarchive(tasks.get(0));
        assertEquals(bagImpl.getTasks(), tasks);
    }

    @Test
    public void delete() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.delete(tasks.get(0));
        ArrayList<Task> emptyTaskList = new ArrayList<>();
        assertEquals(emptyTaskList, bagImpl.getTasks());
    }

    @Test
    public void addAsTask() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text");
        Task task = new Task(1, "Some text");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(mock(Task.class));
        taskList.add(task);
        assertEquals(bagImpl.getTasks(), taskList);

    }
}
