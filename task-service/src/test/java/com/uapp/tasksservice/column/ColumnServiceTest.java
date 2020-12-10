package com.uapp.tasksservice.column;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ColumnServiceTest {

    ColumnService columnService;
    @Mock
    ColumnRepository columnRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(columnRepository.getAllColumns()).thenReturn(getColumns());
        when(columnRepository.getColumnById(anyInt())).thenReturn(getColumn());
        when(columnRepository.delete(anyInt())).thenReturn(true);
        when(columnRepository.update(anyInt(), anyString())).thenReturn(true);
        when(columnRepository.save(any(Column.class))).thenReturn(getColumn());
        when(columnRepository.updateOrder(anyInt(), anyInt())).thenReturn(true);
        columnService = new ColumnService(columnRepository);
    }

    private List<Column> getColumns() {
        Column column = getColumn();
        List<Column> columns = new ArrayList<>();
        columns.add(column);
        return columns;
    }

    private Column getColumn() {
        Column column = new Column("column0");
        column.setId(0);
        return column;
    }

    @Test
    void testSave() {
        Column updatedColumn = columnService.save("column0");
        assertEquals("column0", updatedColumn.getName());
    }

    @Test
    void testUpdate() {
        boolean isUpdated = columnService.update(2, "column0");
        assertTrue(isUpdated);
    }

    @Test
    void testUpdateOrder() {
        boolean isUpdated = columnService.updateOrder(122, 2);
        assertTrue(isUpdated);
    }

    @Test
    void testDelete() {
        boolean isDelete = columnService.delete(1000);
        assertTrue(isDelete);
    }

    @Test
    void getColumnById() {
        Column columnById = columnService.getColumnById(0);
        assertEquals("column0", columnById.getName());
    }

    @Test
    void getAllColumns() {
        List<Column> allColumns = columnService.getAllColumns();
        assertEquals(0, allColumns.get(0).getId());
    }
}