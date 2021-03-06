package com.uapp.tasksservice.columntaskrelation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class ColumnTaskRelationServiceTest {
    @Mock
    ColumnTaskRelationRepository columnTaskRelationRepository;
    ColumnTaskRelationService columnTaskRelationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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
    void testGetColumnByTaskId() {
        when(columnTaskRelationRepository.getByTaskId(anyInt())).thenReturn(getColumnTaskRelation());
        ColumnTaskRelation columnByTaskId = columnTaskRelationService.getColumnByTaskId(0);
        assertEquals(2, columnByTaskId.getTaskId());
    }

    @Test
    void testGetTasksByColumnId() {
        when(columnTaskRelationRepository.getByColumnId(anyInt())).thenReturn(getColumnTaskRelationsList());
        List<ColumnTaskRelation> tasksByColumnId = columnTaskRelationService.getTasksByColumnId(0);
        assertThat(1, is(tasksByColumnId.size()));
    }

    @Test
    void testDeleteByColumnId() {
        when(columnTaskRelationRepository.deleteByColumnId(anyInt())).thenReturn(true);
        boolean isDeleted = columnTaskRelationService.deleteByColumnId(2);
        assertTrue(isDeleted);
    }

    @Test
    void testDeleteByTaskId() {
        when(columnTaskRelationRepository.deleteByTaskId(anyInt())).thenReturn(true);
        boolean isDeleted = columnTaskRelationService.deleteByTaskId(12);
        assertTrue(isDeleted);
    }

    @Test
    void testUpdateColumnIdByTaskId() {
        when(columnTaskRelationRepository.updateColumnIdByTaskId(anyInt(), anyInt())).thenReturn(true);
        boolean isUpdated = columnTaskRelationService.updateColumnIdByTaskId(12, 2200);
        assertTrue(isUpdated);
    }
}