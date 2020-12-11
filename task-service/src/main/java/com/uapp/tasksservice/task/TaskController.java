package com.uapp.tasksservice.task;

import com.uapp.tasksservice.columntaskrelation.ColumnTaskRelationService;
import com.uapp.tasksservice.task.sort.SortingStrategy;
import com.uapp.tasksservice.task.sort.SortingType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskController {
    private final TaskService taskService;
    private final TaskDtoConverter taskDtoConverter;
    private final ColumnTaskRelationService columnTaskRelationService;
    private final SortingStrategy sortingStrategy;

    public TaskController(TaskService taskService, TaskDtoConverter taskDtoConverter, ColumnTaskRelationService columnTaskRelationService, SortingStrategy sortingStrategy) {
        this.taskService = taskService;
        this.taskDtoConverter = taskDtoConverter;
        this.columnTaskRelationService = columnTaskRelationService;
        this.sortingStrategy = sortingStrategy;
    }

    @GetMapping("/tasks/get")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<Task> allTask = taskService.getAllTasks();
        return new ResponseEntity<>(taskDtoConverter.convertAll(allTask), HttpStatus.OK);
    }

    @GetMapping("/tasks/getSortedBy")
    public ResponseEntity<List<TaskDto>> getTaskSortedBy(@RequestParam String sortedType) {
        Comparator<Task> comparator = null;

        for (SortingType type : SortingType.values()) {
            if (type.name().equalsIgnoreCase(sortedType)) {
                comparator = sortingStrategy.getComparator(type);
            }
        }

        if (comparator == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Task> sortedTasks = taskService.getAllTasks().stream().sorted(comparator).collect(Collectors.toList());
        List<TaskDto> taskDtos = taskDtoConverter.convertAll(sortedTasks);

        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    @PostMapping("/task/save")
    public ResponseEntity<TaskDto> saveNewTask(@RequestParam String name,
                                               @RequestParam String description,
                                               @RequestParam LocalDate dateOfCreate) {
        Task savedTask = taskService.save(name, description, dateOfCreate);
        return new ResponseEntity<>(taskDtoConverter.convert(savedTask), HttpStatus.OK);
    }

    @GetMapping("/tasks/get/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable int id) {
        Task taskById = taskService.getTaskById(id);
        return new ResponseEntity<>(taskDtoConverter.convert(taskById), HttpStatus.OK);
    }

    @PutMapping("/tasks/update/{id}")
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

    @DeleteMapping("/tasks/delete/{id}")
    public ResponseEntity<Integer> deleteTaskById(@PathVariable int id) {
        boolean isDelete = taskService.delete(id);
        columnTaskRelationService.deleteByTaskId(id);
        if (!isDelete) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
