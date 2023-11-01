package com.example.revatureproject5.Controller;

import com.example.revatureproject5.JPA.TicketRepository;
import com.example.revatureproject5.JPA.UserRepository;
import com.example.revatureproject5.model.Ticket;
import com.example.revatureproject5.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserTicketsController {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/allTicketsByUser")
    public List<Ticket> showTicketForm(Ticket ticket, @RequestParam("userId_1") Long userId) {
        long id=userId;
        User user = userRepository.findById(id);
        if (user != null) {
            ticket.setUser(user);
            List<Ticket> userTickets = ticketRepository.findByUser(user);
            if (userTickets != null) {
                return userTickets;
            }else{
                System.out.println("the user has no ticket");
            }
            System.out.println("user Id does not exist");
            }
        return null;
        }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/allTicketsByUser")
//    public String userTickets(Ticket ticket, @RequestParam("userId") Long userId) {
    public List<Ticket> userTickets(Ticket ticket, @RequestParam("userId_1") Long userId) {
        //User user = userRepository.findByUserId(userId).orElse(null);
        //System.out.println("passed userid is: " + userId);
        long id = userId;
        User user = userRepository.findById(id);
        System.out.println("userid is: " + user.getUserId());
        System.out.println(user);

        if (user != null) {
            ticket.setUser(user);
            List<Ticket> userTickets = ticketRepository.findByUser(user);
            if (userTickets != null) {
                System.out.println(userTickets);
                String input = userTickets.toString();
                return userTickets;
            } else {
                System.out.println("userTicket is null !");
                // Add the 'userTickets' attribute to the model
                return null;
               // return "redirect:/allTickets"; // Change to the appropriate view name
            }
        } else {
            //return "redirect:/error";
            return null;
        }
    }
}
