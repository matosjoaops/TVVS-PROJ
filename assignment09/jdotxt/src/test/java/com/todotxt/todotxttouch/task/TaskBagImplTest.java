package com.todotxt.todotxttouch.task;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(bagImplTasks, new ArrayList<>());
    }

    @Test
    public void archive() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.archive();
        bagImpl.unarchive(tasks.get(0));
        assertEquals(bagImpl.getTasks(), tasks);
    }

    @Test(expected = TaskPersistException.class)
    public void delete() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.delete(tasks.get(0));
        ArrayList<Task> emptyTaskList = new ArrayList<>();
    }

    @Test(expected = TaskPersistException.class)
    public void delete2() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text");
        Task task = new Task(1, "Some text");
        bagImpl.delete(task);
        ArrayList<Task> emptyTaskList = new ArrayList<>();
    }

    @Test(expected = TaskPersistException.class)
    public void addAsTask() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text");
        Task task = new Task(1, "Some text");
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(mock(Task.class));
        taskList.add(task);

    }

    @Test(expected = TaskPersistException.class)
    public void update1() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text");
        Task task = new Task(1, "Some text", new Date());
        bagImpl.update(task);
    }

    @Test(expected = TaskPersistException.class)
    public void update2() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text");
        Task task = new Task(1, "Some adsfasdf text", new Date());
        assertNotEquals(task, bagImpl.getTasks().get(0));
        bagImpl.update(task);
    }

    @Test
    public void getPriorities1() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        Task task = new Task(1, "Some text");
        task.setPriority(Priority.A);
        bagImpl.store(new ArrayList<>(Arrays.asList(task)));
        ArrayList<Priority> expected = new ArrayList<>(Arrays.asList(Priority.A));
        assertEquals(new ArrayList<>(), bagImpl.getPriorities());
    }

    @Test(expected = TaskPersistException.class)
    public void getPriorities2() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text");
        ArrayList<Priority> expected = new ArrayList<>();
    }

    @Test(expected = TaskPersistException.class)
    public void hasChanged() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("asfdasf");
    }

    @Test(expected = TaskPersistException.class)
    public void getContexts() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text @test some text");
        List<String> contexts = bagImpl.getContexts(true);
        List<String> expected = new ArrayList<>(Arrays.asList("-", "test"));
    }

    @Test(expected = TaskPersistException.class)
    public void getProjects() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text +test some text");
        List<String> contexts = bagImpl.getProjects(true);
        List<String> expected = new ArrayList<>(Arrays.asList("-", "test"));
    }

    @Test(expected = TaskPersistException.class)
    public void clear() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("Some text");
        List<Task> initialTasks = bagImpl.getTasks();
        bagImpl.clear();
        List<Task> finalTasks = bagImpl.getTasks();
    }

    @Test(expected = TaskPersistException.class)
    public void getTasks() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        Filter<Task> filter = new ByTextFilter("test", true);
        bagImpl.addAsTask("Some test");
        bagImpl.addAsTask("Some text");
        List<Task> tasks = bagImpl.getTasks(filter, null);
    }

    @Test
    public void find() {
        Task task1 = new Task(1, "Some text");
        Task task2 = new Task(2, "Some text");
        List<Task> taskList = new ArrayList<>(Arrays.asList(task1, task2));
        Task result = TaskBagImpl.find(taskList, task1);
        assertEquals(task1, result);
    }

    @Test
    public void remote() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.pullFromRemote();
        bagImpl.pushToRemote(true);
    }

    @Test(expected = TaskPersistException.class)
    public void size() {
        TaskBagImpl bagImpl = new TaskBagImpl(repository);
        bagImpl.addAsTask("asfa");
    }
}
