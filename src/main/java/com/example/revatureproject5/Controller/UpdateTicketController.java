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
public class UpdateTicketController {

    @Autowired
    private TicketRepository ticketRepository; // Assuming you have a TicketRepository

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/updateTicket")
    public Ticket updateTicketResolvedStatus(@RequestParam("ticketId") Long ticketId, @RequestParam("resolved") boolean resolved) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            System.out.println(ticket);
            ticket.setResolved(resolved);
            ticketRepository.save(ticket);
            return ticket;
        } else {
            // Handle the case where the ticket with the given ticketId is not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket with ID " + ticketId + " not found");
        }
    }
}

