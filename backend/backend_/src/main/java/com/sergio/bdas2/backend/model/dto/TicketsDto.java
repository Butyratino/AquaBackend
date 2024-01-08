package com.sergio.bdas2.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketsDto {
    private Long ticketId;
    private Timestamp purchaseDate;
    private Long userId;
    private Long ticketTypeId;
    private Long sectionId;
    private Double price;
    private Timestamp dateOfIssue;
    private Long expirationTime;
    private String isActive;
    private Long additionalServiceId;

    public static RowMapper<TicketsDto> getTicketsDtoMapper() {
        return (rs, rowNum) -> {
            TicketsDto ticket = new TicketsDto();
            ticket.setTicketId(rs.getLong("TICKETID"));
            ticket.setPurchaseDate(rs.getTimestamp("PURCHASEDATE"));
            ticket.setUserId(rs.getLong("USERID"));
            ticket.setTicketTypeId(rs.getLong("TICKETTYPEID"));
            ticket.setSectionId(rs.getLong("SECTIONID"));
            ticket.setPrice(rs.getDouble("PRICE"));
            ticket.setDateOfIssue(rs.getTimestamp("DATEOFISSUE"));
            ticket.setExpirationTime(rs.getLong("EXPIRATIONTIME"));
            ticket.setIsActive(rs.getString("ISACTIVE"));
            ticket.setAdditionalServiceId(rs.getLong("ADDITIONALSERVICEID"));
            return ticket;
        };
    }
}
