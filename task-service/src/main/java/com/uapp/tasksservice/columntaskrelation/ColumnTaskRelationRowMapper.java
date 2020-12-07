package com.uapp.tasksservice.columntaskrelation;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ColumnTaskRelationRowMapper implements RowMapper<ColumnTaskRelation> {

    @Override
    public ColumnTaskRelation mapRow(ResultSet rs, int rowNum) throws SQLException {
        ColumnTaskRelation columnTaskRelation = new ColumnTaskRelation();
        columnTaskRelation.setColumnId(rs.getInt("columnId"));
        columnTaskRelation.setTaskId(rs.getInt("taskId"));
        return columnTaskRelation;
    }
}
