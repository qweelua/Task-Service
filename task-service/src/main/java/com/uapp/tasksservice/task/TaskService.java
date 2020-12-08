package com.uapp.tasksservice.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(String name, String description, LocalDate dateOfCreation) {
        Task task = new Task(name, description);
        task.setDescription(description);
        task.setDateOfCreation(dateOfCreation);
        taskRepository.save(task);
    }

    public Task getTaskById(int id) {
        return taskRepository.getTaskById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }
}
