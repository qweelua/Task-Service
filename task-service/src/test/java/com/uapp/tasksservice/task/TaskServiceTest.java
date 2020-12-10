package com.uapp.tasksservice.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    private TaskService taskService;
    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(taskRepository.getAllTasks()).thenReturn(getTestData());
        when(taskRepository.getTaskById(anyInt())).thenReturn(getTask());
        when(taskRepository.save(any(Task.class))).thenReturn(getTask());
        when(taskRepository.update(anyInt(), anyString(), anyString(), any(LocalDate.class))).thenReturn(true);
        when(taskRepository.delete(anyInt())).thenReturn(true);
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
    void testGetTaskById() {
        Task task = taskService.getTaskById(1);
        assertEquals("task0", task.getName());
    }

    @Test
    void testGetAllTasks() {
        List<Task> allTasks = taskService.getAllTasks();
        assertEquals("task0", allTasks.get(0).getName());
    }

    @Test
    void testSave() {
        Task savedTask = taskService.save("task0", "desc0", LocalDate.of(2020, 10, 12));
        assertEquals("task0", savedTask.getName());
    }

    @Test
    void testUpdate() {
        boolean isUpdate = taskService.update(1, "name", "desc", LocalDate.of(2020, 10, 12));
        assertTrue(isUpdate);
    }

    @Test
    void testDelete() {
        boolean isDelete = taskService.delete(0);
        assertTrue(isDelete);
    }
}