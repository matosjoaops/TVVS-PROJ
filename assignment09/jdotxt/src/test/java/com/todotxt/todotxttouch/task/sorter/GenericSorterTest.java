package com.todotxt.todotxttouch.task.sorter;

import com.todotxt.todotxttouch.task.Task;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GenericSorterTest {
    @Test
    public void buildGenericSorterTest() {
        GenericSorter<Task> genericSorter = new GenericSorter<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return 0;
            }
        };

        Sorter<Task> sorter1 = new GenericSorter<>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.getText().length() - o2.getText().length();
            }
        };

        Sorter<Task> sorter2 = new GenericSorter<>() {
            @Override
            public int compare(Task o1, Task o2) {
                return 0;
            }
        };

        Task task1 = new Task(1, "task1");
        Task task2 = new Task(2, "task 2");

        assertEquals(genericSorter.buildGenericSorter(sorter1, sorter2).compare(task1, task2), -1);
        assertEquals(genericSorter.buildGenericSorter(sorter1, sorter2).compare(task1, task1), 0);
    }
}
