package com.uapp.tasksservice.column;

import com.uapp.tasksservice.columntaskrelation.ColumnTaskRelation;
import com.uapp.tasksservice.columntaskrelation.ColumnTaskRelationService;
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

    @Autowired
    public ColumnController(ColumnService columnService, ColumnDtoConverter columnDtoConverter, ColumnTaskRelationService columnTaskRelationService, TaskService taskService) {
        this.columnService = columnService;
        this.columnDtoConverter = columnDtoConverter;
        this.columnTaskRelationService = columnTaskRelationService;
        this.taskService = taskService;
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

    private void deleteTasksThatWhereInColumn(List<ColumnTaskRelation> tasksByColumnId) {
        tasksByColumnId.stream().map(ColumnTaskRelation::getTaskId).forEach(taskService::delete);
    }

}
