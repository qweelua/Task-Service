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

    public Column save(Column column) {
        String sql = "INSERT INTO columns(name, \"order\") VALUES(:name, :order)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", column.getName());
        params.addValue("order", column.getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});

        int id = keyHolder.getKey().intValue();
        column.setId(id);
        return column;

    }

    public boolean update(int id, String name) {
        String sql = "UPDATE columns SET name = :name WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("id", id);

        int update = jdbcTemplate.update(sql, params);
        return update != 0;
    }

    public boolean updateOrder(int id, int order) {
        String sql = "UPDATE columns SET \"order\" = :order WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("order", order);

        int update = jdbcTemplate.update(sql, params);
        return update != 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM columns WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        int update = jdbcTemplate.update(sql, params);
        return update != 0;
    }

    public Column getColumnById(int id) {
        String sql = "SELECT id, name, \"order\" FROM columns WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, rowMapper);
    }

    public List<Column> getAllColumns() {
        String sql = "SELECT id, name, \"order\" FROM columns";
        return jdbcTemplate.query(sql, rowMapper);
    }

}
