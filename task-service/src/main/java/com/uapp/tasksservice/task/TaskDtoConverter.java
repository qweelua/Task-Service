package com.uapp.tasksservice.task;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDtoConverter {

    public TaskDto convert(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setDateOfCreation(task.getDateOfCreation());
        return taskDto;
    }

    public List<TaskDto> convertAll(List<Task> tasks) {
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskDtos.add(convert(task));
        }
        return taskDtos;
    }
}
