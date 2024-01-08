package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {
    private Long id;
    private LocalDateTime timestamp;
    private String username;
    private String eventDescription;

    public static RowMapper<LogDto> getLogDtoMapper() {
        return (rs, rowNum) -> {
            LogDto logDTO = new LogDto();
            logDTO.setId(rs.getLong("LOGID"));
            logDTO.setTimestamp(
                    rs.getTimestamp("LOG_TIMESTAMP").toLocalDateTime()
            );
            logDTO.setUsername(rs.getString("USERNAME"));
            logDTO.setEventDescription(rs.getString("EVENT_DESCRIPTION"));
            return logDTO;
        };
    }

}
