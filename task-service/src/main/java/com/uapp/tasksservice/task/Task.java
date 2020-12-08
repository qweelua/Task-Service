package com.uapp.tasksservice.task;

import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private int id;
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
        return getId() == task.getId() &&
                getName().equals(task.getName()) &&
                getDescription().equals(task.getDescription()) &&
                getDateOfCreation().equals(task.getDateOfCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getDateOfCreation());
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateOfCreation=" + dateOfCreation +
                '}';
    }
}
