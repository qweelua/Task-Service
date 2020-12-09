package com.uapp.tasksservice.task;

import com.uapp.tasksservice.columntaskrelation.ColumnTaskRelationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private final TaskService taskService;
    private final TaskDtoConverter taskDtoConverter;
    private final ColumnTaskRelationService columnTaskRelationService;

    public TaskController(TaskService taskService, TaskDtoConverter taskDtoConverter, ColumnTaskRelationService columnTaskRelationService) {
        this.taskService = taskService;
        this.taskDtoConverter = taskDtoConverter;
        this.columnTaskRelationService = columnTaskRelationService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> allTaskDtos = taskService.getAllTasks().stream().map(taskDtoConverter::convert).collect(Collectors.toList());
        return new ResponseEntity<>(allTaskDtos, HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDto> saveNewTask(@RequestParam String name,
                                               @RequestParam String description,
                                               @RequestParam LocalDate dateOfCreate) {
        Task savedTask = taskService.save(name, description, dateOfCreate);
        return new ResponseEntity<>(taskDtoConverter.convert(savedTask), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable int id) {
        Task taskById = taskService.getTaskById(id);
        return new ResponseEntity<>(taskDtoConverter.convert(taskById), HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Integer> updateTaskById(@PathVariable int id,
                                                  @RequestParam String name,
                                                  @RequestParam String description,
                                                  @RequestParam LocalDate dateOfCreate,
                                                  @RequestParam int columnId) {
        boolean isUpdate = taskService.update(id, name, description, dateOfCreate);
        columnTaskRelationService.save(columnId, id);
        if (!isUpdate) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Integer> deleteTaskById(@PathVariable int id) {
        boolean isDelete = taskService.delete(id);
        columnTaskRelationService.deleteByTaskId(id);
        if (!isDelete) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
