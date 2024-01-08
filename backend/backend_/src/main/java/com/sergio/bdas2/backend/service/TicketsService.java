package com.sergio.bdas2.backend.service;

import com.sergio.bdas2.backend.model.dto.TicketsDto;
import com.sergio.bdas2.backend.repository.TicketsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketsService {

    private final TicketsDao ticketsDao;
    private static final Logger logger = LoggerFactory.getLogger(TicketsService.class);

    public List<TicketsDto> getAllTickets() {
        return ticketsDao.getAllTickets();
    }

    public TicketsDto getTicketById(Long ticketId) {
        return ticketsDao.getTicketById(ticketId);
    }

    public void updateTicket(Long id, TicketsDto ticket) {
        ticketsDao.updateTicket(id, ticket);
    }

    public void addTicket(TicketsDto ticket) {
        logger.info("Adding new ticket: {}", ticket);
        ticketsDao.addTicket(ticket);
    }

    public void deleteTicket(Long ticketId) {
        ticketsDao.deleteTicket(ticketId);
    }

    // Add other methods as needed
}
