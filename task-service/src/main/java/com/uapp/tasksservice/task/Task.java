package com.uapp.tasksservice.task;

import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private int id;
    private int userId;
    private String name;
    private String description;
    private LocalDate dateOfCreation;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        dateOfCreation = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                userId == task.userId &&
                name.equals(task.name) &&
                description.equals(task.description) &&
                dateOfCreation.equals(task.dateOfCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, name, description, dateOfCreation);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
