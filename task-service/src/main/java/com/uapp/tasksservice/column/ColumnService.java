package com.uapp.tasksservice.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColumnService {
    private final ColumnRepository columnRepository;

    @Autowired
    public ColumnService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public void save(int id, String name) {
        Column column = new Column(name);
        column.setId(id);
        columnRepository.save(column);
    }
}
