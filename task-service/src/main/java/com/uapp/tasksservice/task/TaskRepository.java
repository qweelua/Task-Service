package com.uapp.tasksservice.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    public Task save(Task task) {
        String sql = "INSERT INTO tasks(name, description, \"dateOfCreate\") VALUES (:name, :description, :date)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", task.getName());
        params.addValue("description", task.getDescription());
        params.addValue("date", task.getDateOfCreation());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        int id = keyHolder.getKey().intValue();
        task.setId(id);

        return task;
    }

    public boolean update(int id, String name, String description, LocalDate dateOfCreate) {
        String sql =
                "UPDATE tasks SET name = :name, description = :description, \"dateOfCreate\" = :dateOfCreate WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", name);
        params.addValue("description", description);
        params.addValue("dateOfCreate", dateOfCreate);

        int update = jdbcTemplate.update(sql, params);
        return update != 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM tasks WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        int update = jdbcTemplate.update(sql, params);
        return update != 0;
    }

    public Task getTaskById(int id) {
        String sql = "SELECT * FROM tasks WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, taskRowMapper);
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT id, name, description, \"dateOfCreate\" FROM tasks";
        return jdbcTemplate.query(sql, taskRowMapper);
    }
}
