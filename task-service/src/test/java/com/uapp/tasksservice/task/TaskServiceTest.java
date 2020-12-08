package com.uapp.tasksservice.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(taskRepository.getAllTasks()).thenReturn(getTestData());
        when(taskRepository.getTaskById(0)).thenReturn(getTask());
        taskService = new TaskService(taskRepository);
    }

    private List<Task> getTestData() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task task = getTask();
        tasks.add(task);
        return tasks;
    }

    private Task getTask() {
        Task task = new Task("task0", "desc0");
        task.setId(0);
        return task;
    }

    @Test
    void getTaskById() {
        Task task = taskService.getTaskById(0);
        assertEquals("task0", task.getName());
    }

    @Test
    void getAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        assertEquals("task0", allTasks.get(0).getName());
    }
}