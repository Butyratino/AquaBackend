package com.sergio.bdas2.backend.model.entity;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

@Data
public class Tickets {
    private int ticketId;
    private LocalDateTime purchaseDate;
    private int userId;

    public static RowMapper<Tickets> getTicketsMapper() {
        return (rs, rowNum) -> {
            Tickets tickets = new Tickets();
            tickets.setTicketId(rs.getInt("USERID"));
            tickets.setPurchaseDate(rs.getTimestamp("LOGIN").toLocalDateTime());
            tickets.setUserId(rs.getInt("USERID"));

            return tickets;
        };
    }

}
