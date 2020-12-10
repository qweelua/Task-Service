package com.uapp.tasksservice.column;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ColumnRowMapper implements RowMapper<Column> {
    @Override
    public Column mapRow(ResultSet rs, int rowNum) throws SQLException {
        Column column = new Column(rs.getString("name"));
        column.setId(rs.getInt("id"));
        column.setOrder(rs.getInt("order"));
        return column;
    }
}
