package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.LogDto;
import com.sergio.bdas2.backend.model.entity.TableColumn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LogDao {

    private final JdbcTemplate jdbcTemplate;

    public LogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TableColumn> getTableColumns() {
        String sql = "SELECT table_name, column_name, data_type, data_length FROM user_tab_columns ORDER BY table_name, column_id";
        return jdbcTemplate.query(sql, new RowMapper<TableColumn>() {
            @Override
            public TableColumn mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new TableColumn(
                        rs.getString("table_name"),
                        rs.getString("column_name"),
                        rs.getString("data_type"),
                        rs.getInt("data_length")
                );
            }
        });
    }

    public List<LogDto> getAllLogs() {
        String query = "SELECT * FROM LOGS";
        return jdbcTemplate.query(query, LogDto.getLogDtoMapper());
    }







}
