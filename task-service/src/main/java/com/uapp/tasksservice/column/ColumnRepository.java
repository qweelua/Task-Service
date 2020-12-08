package com.uapp.tasksservice.column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ColumnRepository {
    private final ColumnRowMapper rowMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ColumnRepository(ColumnRowMapper rowMapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.rowMapper = rowMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Column column) {
        String sql = "INSERT INTO columns(name) VALUES(:name)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", column.getName());

        jdbcTemplate.update(sql, params);
    }

    public Column getColumnById(int id) {
        String sql = "SELECT id, name FROM columns WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, rowMapper);
    }

    public List<Column> getAllColumns() {
        String sql = "SELECT id, name FROM columns";
        return jdbcTemplate.query(sql,rowMapper);
    }

}
