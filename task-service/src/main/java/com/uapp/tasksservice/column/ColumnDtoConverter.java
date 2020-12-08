package com.uapp.tasksservice.column;

import org.springframework.stereotype.Component;

@Component
public class ColumnDtoConverter {

    public ColumnDto convert(Column column) {
        ColumnDto columnDto = new ColumnDto();
        columnDto.setId(column.getId());
        columnDto.setName(column.getName());
        return columnDto;
    }
}
