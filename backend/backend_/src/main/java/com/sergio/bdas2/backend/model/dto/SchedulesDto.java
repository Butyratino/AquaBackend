package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulesDto {

    private Long scheduleId;
    private Long sectionId;
    private String dayOfWeek;
    private Time openingHours;
    private Time closingHours;

    public static RowMapper<SchedulesDto> getSchedulesDtoMapper() {
        return (rs, rowNum) -> {
            SchedulesDto schedule = new SchedulesDto();
            schedule.setScheduleId(rs.getLong("SCHEDULEID"));
            schedule.setSectionId(rs.getLong("SECTIONID"));
            schedule.setDayOfWeek(rs.getString("DAYOFWEEK"));
            schedule.setOpeningHours(rs.getTime("OPENINGHOURS"));
            schedule.setClosingHours(rs.getTime("CLOSINGHOURS"));
            return schedule;
        };
    }
}
