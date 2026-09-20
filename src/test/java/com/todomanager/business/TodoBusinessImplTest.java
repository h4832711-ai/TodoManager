package com.todomanager.business;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.todomanager.data.SimpleTodoService;

public class TodoBusinessImplTest {

    private SimpleTodoService service;
    private TodoBusinessImpl manager;

    @Before
    public void setUp() {
        service = new SimpleTodoService(Arrays.asList(
                "Learn Spring",
                "Learn Java",
                "Practice Spring Boot"
        ));

        manager = new TodoBusinessImpl(service);
    }

    @Test
    public void returnsOnlySpringTasks() {
        List<String> result =
                manager.retrieveTodosRelatedToSpring("student");

        assertEquals(Arrays.asList(
                "Learn Spring",
                "Practice Spring Boot"
        ), result);
    }

    @Test
    public void returnsEmptyListWhenThereAreNoTasks() {
        SimpleTodoService emptyService =
                new SimpleTodoService(Collections.emptyList());

        TodoBusinessImpl emptyManager =
                new TodoBusinessImpl(emptyService);

        assertTrue(
                emptyManager.retrieveTodosRelatedToSpring("student")
                        .isEmpty()
        );
    }

    @Test
    public void returnsEmptyListWhenNoTasksMatch() {
        SimpleTodoService otherService =
                new SimpleTodoService(Arrays.asList(
                        "Learn Java",
                        "Write tests"
                ));

        TodoBusinessImpl otherManager =
                new TodoBusinessImpl(otherService);

        assertTrue(
                otherManager.retrieveTodosRelatedToSpring("student")
                        .isEmpty()
        );
    }

    @Test
    public void returnsAllTasksWhenTheyAllMatch() {
        SimpleTodoService springService =
                new SimpleTodoService(Arrays.asList(
                        "Learn Spring",
                        "Practice Spring Boot"
                ));

        TodoBusinessImpl springManager =
                new TodoBusinessImpl(springService);

        assertArrayEquals(
                new String[]{
                        "Learn Spring",
                        "Practice Spring Boot"
                },
                springManager.retrieveTodosRelatedToSpring("student")
                        .toArray(new String[0])
        );
    }

    @Test
    public void deletesNonSpringTasksAndKeepsMatchingTasks() {
        manager.deleteTodosNotRelatedToSpring("student");

        List<String> remaining = service.retrieveTodos("student");

        assertFalse(remaining.contains("Learn Java"));

        assertEquals(Arrays.asList(
                "Learn Spring",
                "Practice Spring Boot"
        ), remaining);
    }

    @Test
    public void filteringDoesNotChangeOriginalTasks() {
        manager.retrieveTodosRelatedToSpring("student");

        assertEquals(Arrays.asList(
                "Learn Spring",
                "Learn Java",
                "Practice Spring Boot"
        ), service.retrieveTodos("student"));
    }
}