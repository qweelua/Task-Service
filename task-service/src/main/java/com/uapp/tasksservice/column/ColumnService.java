package com.uapp.tasksservice.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService {
    private final ColumnRepository columnRepository;

    @Autowired
    public ColumnService(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    public Column save(String name) {
        Column column = new Column(name);
        return columnRepository.save(column);
    }

    public boolean update(int id, String name) {
        return columnRepository.update(id, name);
    }

    public boolean delete(int id) {
        return columnRepository.delete(id);
    }

    public Column getColumnById(int id) {
        return columnRepository.getColumnById(id);
    }

    public List<Column> getAllColumns() {
        return columnRepository.getAllColumns();
    }
}
