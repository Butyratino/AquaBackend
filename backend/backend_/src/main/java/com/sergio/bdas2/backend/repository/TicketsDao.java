package com.sergio.bdas2.backend.repository;


import com.sergio.bdas2.backend.model.entity.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Tickets getTicketsById(Integer ticketId){
        String query = "SELECT * FROM TICKETS WHERE ID = ?";
        List<Tickets> foundTickets  = jdbcTemplate.query(query, new Object[]{ticketId},
                Tickets.getTicketsMapper());
        if (foundTickets.size() != 1){
            throw new DaoException("Ticket with ID " + ticketId + " not found");
        }
        return foundTickets.get(0);
    }
}
