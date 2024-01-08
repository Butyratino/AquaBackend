package com.sergio.bdas2.backend.controller;

import com.sergio.bdas2.backend.model.dto.TicketsDto;
import com.sergio.bdas2.backend.service.TicketsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpMethod;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {

    private final TicketsService ticketsService;

    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @GetMapping("/all")
    public List<TicketsDto> getAllTickets() {
        List<TicketsDto> tickets = ticketsService.getAllTickets();
        System.out.println(tickets.size());
        return tickets;
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketsDto> getTicketById(@PathVariable Long ticketId) {
        TicketsDto ticket = ticketsService.getTicketById(ticketId);
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTicket(@RequestBody TicketsDto ticket) {
        System.out.println("Received POST request to add a ticket");
        ticketsService.addTicket(ticket);
        return new ResponseEntity<>("Ticket added successfully", HttpStatus.CREATED);
    }

    // Add the following method to handle OPTIONS requests
    @RequestMapping(value = "/add", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        System.out.println("Received OPTIONS request for adding a ticket");
        return ResponseEntity.ok().allow(HttpMethod.POST).build();
    }

    @PutMapping("/update/{ticketId}")
    public ResponseEntity<Void> updateTicket(@PathVariable Long ticketId, @RequestBody TicketsDto ticket) {
        ticket.setTicketId(ticketId);
        ticketsService.updateTicket(ticketId, ticket);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long ticketId) {
        ticketsService.deleteTicket(ticketId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
