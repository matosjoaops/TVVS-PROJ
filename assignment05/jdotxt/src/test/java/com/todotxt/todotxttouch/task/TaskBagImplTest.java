package com.todotxt.todotxttouch.task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    @Test
    public void update() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text");
        Task task = new Task(1, "Some text", new Date());
        assertNotEquals(task, bagImpl.getTasks().get(0));
        bagImpl.update(task);
        assertEquals(task, bagImpl.getTasks().get(0));
    }

    @Test
    public void getPriorities() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        Task task = new Task(1, "Some text");
        task.setPriority(Priority.A);
        bagImpl.store(new ArrayList<>(Arrays.asList(task)));
        ArrayList<Priority> expected = new ArrayList<>(Arrays.asList(Priority.A));
        assertEquals(expected, bagImpl.getPriorities());
    }

    @Test
    public void getContexts() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text @test some text");
        List<String> contexts = bagImpl.getContexts(true);
        List<String> expected = new ArrayList<>(Arrays.asList("-", "test"));
        assertEquals(expected, contexts);
    }

    @Test
    public void getProjects() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text +test some text");
        List<String> contexts = bagImpl.getProjects(true);
        List<String> expected = new ArrayList<>(Arrays.asList("-", "test"));
        assertEquals(expected, contexts);
    }
}
