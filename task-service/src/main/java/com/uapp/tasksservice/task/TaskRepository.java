package com.uapp.tasksservice.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final TaskRowMapper taskRowMapper;

    @Autowired
    public TaskRepository(NamedParameterJdbcTemplate jdbcTemplate, TaskRowMapper taskRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.taskRowMapper = taskRowMapper;
    }

    public void save(Task task) {
        String sql = "INSERT INTO tasks(\"userId\", name, description, \"dateOfCreate\") VALUES (:userId, :name, :description, :date)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", task.getUserId());
        params.addValue("name", task.getName());
        params.addValue("description", task.getDescription());
        params.addValue("date", task.getDateOfCreation());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});

        int id = keyHolder.getKey().intValue();
        task.setId(id);
    }

    public Task getTaskById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, taskRowMapper);
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT id, \"userId\", name, description, \"dateOfCreate\" FROM tasks";
        return jdbcTemplate.query(sql, taskRowMapper);
    }
}
