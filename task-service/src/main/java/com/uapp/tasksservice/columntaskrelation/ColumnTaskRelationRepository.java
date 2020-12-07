package com.uapp.tasksservice.columntaskrelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ColumnTaskRelationRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ColumnTaskRelationRowMapper rowMapper;

    @Autowired
    public ColumnTaskRelationRepository(NamedParameterJdbcTemplate jdbcTemplate, ColumnTaskRelationRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    public void save(ColumnTaskRelation columnTaskRelation) {

        String sql = "INSERT INTO column_task_relation(\"columnId\", \"taskId\") VALUES (:columnId, :taskId)";

        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("columnId", columnTaskRelation.getColumnId());
        param.addValue("taskId", columnTaskRelation.getTaskId());

        jdbcTemplate.update(sql, param);
    }

    public List<ColumnTaskRelation> getByColumnId(int columnId) {
        String sql = "SELECT \"columnId\",\"taskId\" FROM column_task_relation WHERE \"columnId\" = " + columnId;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<ColumnTaskRelation> getByTaskId(int taskId) {
        String sql = "SELECT \"columnId\",\"taskId\" FROM column_task_relation WHERE \"taskId\" = " + taskId;
        return jdbcTemplate.query(sql, rowMapper);
    }

}
