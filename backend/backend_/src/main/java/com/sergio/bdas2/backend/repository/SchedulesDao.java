package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.SchedulesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SchedulesDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SchedulesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SchedulesDto> getAllSchedules() {
        String query = "SELECT * FROM Schedules ORDER BY SCHEDULEID";
        return jdbcTemplate.query(query, SchedulesDto.getSchedulesDtoMapper());
    }

    public SchedulesDto getScheduleById(Long id) {
        String query = "SELECT * FROM Schedules WHERE SCHEDULEID = ?";
        List<SchedulesDto> foundSchedules = jdbcTemplate.query(query, new Object[]{id}, SchedulesDto.getSchedulesDtoMapper());
        if (foundSchedules.size() != 1) {
            throw new DaoException("Schedule with ID " + id + " not found or not unique");
        }
        return foundSchedules.get(0);
    }

    public void addSchedule(SchedulesDto schedule) {
        String query = "INSERT INTO Schedules (SECTIONID, DAYOFWEEK, OPENINGHOURS, CLOSINGHOURS) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
                query,
                schedule.getSectionId(),
                schedule.getDayOfWeek(),
                schedule.getOpeningHours(),
                schedule.getClosingHours()
        );
    }

    public void updateSchedule(Long id, SchedulesDto schedule) {
        String query = "UPDATE Schedules SET SECTIONID = ?, DAYOFWEEK = ?, OPENINGHOURS = ?, CLOSINGHOURS = ? WHERE SCHEDULEID = ?";
        jdbcTemplate.update(query, schedule.getSectionId(), schedule.getDayOfWeek(), schedule.getOpeningHours(), schedule.getClosingHours(), id);
    }

    public void deleteSchedule(Long scheduleId) {
        String query = "DELETE FROM Schedules WHERE SCHEDULEID = ?";
        jdbcTemplate.update(query, scheduleId);
    }
}
