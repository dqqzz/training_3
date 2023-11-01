package com.example.revatureproject5.Controller;

import com.example.revatureproject5.JPA.TicketRepository;
import com.example.revatureproject5.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class DeleteController {

    @Autowired
    private TicketRepository ticketRepository; // Assuming you have a TicketRepository

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/deleteTicket")
    public String deleteTicket(@RequestParam("ticketId") Long ticketId) {
        ticketRepository.deleteById(ticketId);

        Optional<Ticket> ticket = ticketRepository.findById(ticketId);

        if (ticket.isEmpty()) {
            return "Ticket deleted successfully";
        } else {
            // Handle the case where the ticket with the given ticketId is not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket with ID " + ticketId + " not found");
        }
    }
}

