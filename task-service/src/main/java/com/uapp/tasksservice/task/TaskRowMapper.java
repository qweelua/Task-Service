package com.uapp.tasksservice.task;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Component
public class TaskRowMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task(rs.getString("name"), rs.getString("description"));
        task.setId(rs.getInt("id"));
        task.setDateOfCreation(LocalDate.parse(rs.getString("dateOfCreation")));
        return task;
    }
}
