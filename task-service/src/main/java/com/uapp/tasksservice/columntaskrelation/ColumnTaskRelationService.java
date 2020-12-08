package com.uapp.tasksservice.columntaskrelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnTaskRelationService {
    private final ColumnTaskRelationRepository columnTaskRelationRepository;

    @Autowired
    public ColumnTaskRelationService(ColumnTaskRelationRepository columnTaskRelationRepository) {
        this.columnTaskRelationRepository = columnTaskRelationRepository;
    }

    public void save(int columnId, int taskId) {
        ColumnTaskRelation columnTaskRelation = new ColumnTaskRelation();
        columnTaskRelation.setTaskId(taskId);
        columnTaskRelation.setColumnId(columnId);
        columnTaskRelationRepository.save(columnTaskRelation);
    }

    public ColumnTaskRelation getColumnByTaskId(int taskId) {
        return columnTaskRelationRepository.getByTaskId(taskId);
    }

    public List<ColumnTaskRelation> getTasksByColumnId(int columnId) {
        return columnTaskRelationRepository.getByColumnId(columnId);
    }
}
