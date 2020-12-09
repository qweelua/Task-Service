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

    public Task save(String name, String description, LocalDate dateOfCreation) {
        Task task = new Task(name, description);
        task.setDescription(description);
        task.setDateOfCreation(dateOfCreation);
        return taskRepository.save(task);
    }

    public boolean delete(int id) {
        return taskRepository.delete(id);
    }

    public boolean update(int id, String name, String description, LocalDate dateOfCreation) {
        return taskRepository.update(id, name, description, dateOfCreation);
    }

    public Task getTaskById(int id) {
        return taskRepository.getTaskById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }
}
