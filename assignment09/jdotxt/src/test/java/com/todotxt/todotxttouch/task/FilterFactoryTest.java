package com.todotxt.todotxttouch.task;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilterFactoryTest {

    @Test
    public void createFactory() {
        FilterFactory factory = new FilterFactory();
        assertNotNull(factory);
    }

    @Test
    public void generateAndFilter() {
        List<Priority> priorities = new ArrayList<>(Arrays.asList(Priority.A));
        List<String> contexts = new ArrayList<>(Arrays.asList("testContext"));
        List<String> projects = new ArrayList<>(Arrays.asList("testProject"));
        String text = "Some text";
        boolean caseSensitive = true;
        boolean showHidden = false;
        boolean showThreshold = false;
        FilterFactory.generateAndFilter(priorities, contexts, projects, text, caseSensitive, showHidden, showThreshold);
    }
}
