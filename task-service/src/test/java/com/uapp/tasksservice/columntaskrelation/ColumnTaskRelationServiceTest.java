package com.uapp.tasksservice.columntaskrelation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

class ColumnTaskRelationServiceTest {
    @Mock
    ColumnTaskRelationRepository columnTaskRelationRepository;
    ColumnTaskRelationService columnTaskRelationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(columnTaskRelationRepository.getByColumnId(0)).thenReturn(getColumnTaskRelationsList());
        when(columnTaskRelationRepository.getByTaskId(0)).thenReturn(getColumnTaskRelation());
        columnTaskRelationService = new ColumnTaskRelationService(columnTaskRelationRepository);
    }

    private List<ColumnTaskRelation> getColumnTaskRelationsList() {
        List<ColumnTaskRelation> relations = new ArrayList<>();
        relations.add(getColumnTaskRelation());
        return relations;
    }

    private ColumnTaskRelation getColumnTaskRelation() {
        ColumnTaskRelation columnTaskRelation = new ColumnTaskRelation();
        columnTaskRelation.setColumnId(1);
        columnTaskRelation.setTaskId(2);
        return columnTaskRelation;
    }

    @Test
    void getColumnByTaskId() {
        ColumnTaskRelation columnByTaskId = columnTaskRelationService.getColumnByTaskId(0);
        assertEquals(2, columnByTaskId.getTaskId());
    }

    @Test
    void getTasksByColumnId() {
        List<ColumnTaskRelation> tasksByColumnId = columnTaskRelationService.getTasksByColumnId(0);
        assertThat(1, is(tasksByColumnId.size()));
    }
}