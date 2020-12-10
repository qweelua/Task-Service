package com.uapp.tasksservice.column;

import com.uapp.tasksservice.columntaskrelation.ColumnTaskRelation;
import com.uapp.tasksservice.columntaskrelation.ColumnTaskRelationService;
import com.uapp.tasksservice.task.Task;
import com.uapp.tasksservice.task.TaskDto;
import com.uapp.tasksservice.task.TaskDtoConverter;
import com.uapp.tasksservice.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ColumnController {
    private final ColumnService columnService;
    private final ColumnDtoConverter columnDtoConverter;
    private final ColumnTaskRelationService columnTaskRelationService;
    private final TaskService taskService;
    private final TaskDtoConverter taskDtoConverter;

    @Autowired
    public ColumnController(ColumnService columnService, ColumnDtoConverter columnDtoConverter, ColumnTaskRelationService columnTaskRelationService, TaskService taskService, TaskDtoConverter taskDtoConverter) {
        this.columnService = columnService;
        this.columnDtoConverter = columnDtoConverter;
        this.columnTaskRelationService = columnTaskRelationService;
        this.taskService = taskService;
        this.taskDtoConverter = taskDtoConverter;
    }

    @GetMapping("/column")
    public ResponseEntity<List<ColumnDto>> getAllColumns() {
        return new ResponseEntity<>(columnService.getAllColumns()
                .stream()
                .map(columnDtoConverter::convert)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/column")
    public ResponseEntity<ColumnDto> addColumn(@RequestParam String name) {
        return new ResponseEntity<>(columnDtoConverter.convert(columnService.save(name)), HttpStatus.OK);
    }

    @PutMapping("/column")
    public ResponseEntity<int[]> updateOrder(@RequestParam int[] orders) {
        for (int i = 0; i < orders.length; i++) {
            boolean isUpdate = columnService.updateOrder(orders[i], i);
            if (!isUpdate) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/column/{id}")
    public ResponseEntity<ColumnDto> getColumnById(@PathVariable int id) {
        Column columnById = columnService.getColumnById(id);
        if (columnById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(columnDtoConverter.convert(columnById), HttpStatus.OK);
    }

    @PutMapping("/column/{id}")
    public ResponseEntity<Integer> updateColumn(@PathVariable int id, @RequestParam String name) {
        boolean isUpdate = columnService.update(id, name);
        if (!isUpdate) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/column/{id}")
    public ResponseEntity<Integer> deleteColumn(@PathVariable int id) {
        boolean isDelete = columnService.delete(id);
        deleteTasksThatWhereInColumn(columnTaskRelationService.getTasksByColumnId(id));
        columnTaskRelationService.deleteByColumnId(id);
        if (!isDelete) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("column/{id}/tasks")
    public ResponseEntity<List<TaskDto>> getTasksByColumnId(@PathVariable int id) {
        List<ColumnTaskRelation> tasksByColumnId = columnTaskRelationService.getTasksByColumnId(id);
        List<Task> tasks = getAvailableTasks(tasksByColumnId);
        List<TaskDto> taskDtos = tasks.stream().map(taskDtoConverter::convert).collect(Collectors.toList());
        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    private List<Task> getAvailableTasks(List<ColumnTaskRelation> tasksByColumnId) {
        return tasksByColumnId.stream().map(ColumnTaskRelation::getTaskId).map(taskService::getTaskById).collect(Collectors.toList());
    }

    private void deleteTasksThatWhereInColumn(List<ColumnTaskRelation> tasksByColumnId) {
        tasksByColumnId.stream().map(ColumnTaskRelation::getTaskId).forEach(taskService::delete);
    }

}
