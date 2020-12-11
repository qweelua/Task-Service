package com.uapp.tasksservice.column;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ColumnDtoConverter {

    public ColumnDto convert(Column column) {
        ColumnDto columnDto = new ColumnDto();
        columnDto.setId(column.getId());
        columnDto.setName(column.getName());
        return columnDto;
    }

    public List<ColumnDto> convertAll(List<Column> columns) {
        List<ColumnDto> columnDtos = new ArrayList<>();
        for (Column column : columns) {
            columnDtos.add(convert(column));
        }
        return columnDtos;
    }
}
