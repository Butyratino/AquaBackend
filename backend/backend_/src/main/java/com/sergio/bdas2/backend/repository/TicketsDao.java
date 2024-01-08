package com.sergio.bdas2.backend.repository;

import com.sergio.bdas2.backend.model.dto.TicketsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketsDao {

    private final JdbcTemplate jdbcTemplate;

    public void addTicket(TicketsDto ticket) {
        String query = "INSERT INTO TICKETS_TABLE (PURCHASEDATE, USERID, TICKETTYPEID, SECTIONID, PRICE, DATEOFISSUE, EXPIRATIONTIME, ISACTIVE, ADDITIONALSERVICEID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                query,
                ticket.getPurchaseDate(),
                ticket.getUserId(),
                ticket.getTicketTypeId(),
                ticket.getSectionId(),
                ticket.getPrice(),
                ticket.getDateOfIssue(),
                ticket.getExpirationTime(),
                ticket.getIsActive(),
                ticket.getAdditionalServiceId()
        );
    }

    public List<TicketsDto> getAllTickets() {
        System.out.println("getAllTickets was called!!!");
        String query = "SELECT * FROM TICKETS ORDER BY TICKETID";
        return jdbcTemplate.query(query, TicketsDto.getTicketsDtoMapper());
    }

    public TicketsDto getTicketById(Long id) {
        String query = "SELECT * FROM TICKETS WHERE TICKETID = ?";
        List<TicketsDto> foundTickets = jdbcTemplate.query(query, new Object[]{id}, TicketsDto.getTicketsDtoMapper());
        if (foundTickets.size() != 1) {
            throw new DaoException("Ticket with ID " + id + " not found or not unique");
        }
        return foundTickets.get(0);
    }

    public void updateTicket(Long id, TicketsDto ticket) {
        String sql = "UPDATE TICKETS SET PURCHASEDATE=?, USERID=?, TICKETTYPEID=?, SECTIONID=?, PRICE=?, " +
                "DATEOFISSUE=?, EXPIRATIONTIME=?, ISACTIVE=?, ADDITIONALSERVICEID=? WHERE TICKETID=?";
        jdbcTemplate.update(
                sql,
                ticket.getPurchaseDate(),
                ticket.getUserId(),
                ticket.getTicketTypeId(),
                ticket.getSectionId(),
                ticket.getPrice(),
                ticket.getDateOfIssue(),
                ticket.getExpirationTime(),
                ticket.getIsActive(),
                ticket.getAdditionalServiceId(),
                id
        );
    }

    public void deleteTicket(Long ticketId) {
        String query = "DELETE FROM TICKETS WHERE TICKETID=?";
        jdbcTemplate.update(query, ticketId);
    }
}
