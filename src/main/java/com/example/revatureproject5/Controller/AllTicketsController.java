package com.example.revatureproject5.Controller;

import com.example.revatureproject5.JPA.TicketRepository;
import com.example.revatureproject5.JPA.UserRepository;
import com.example.revatureproject5.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AllTicketsController {
    @Autowired
    private TicketRepository ticketRepository;
    private  UserRepository user;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/allTickets")
    public List<Ticket> showTicketForm(@RequestParam("userId_2") String userId) {

       return ticketRepository.findAll();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/allTickets")
//    public String userTickets(Ticket ticket, @RequestParam("userId") Long userId) {
    public List<Ticket> allTickets(@RequestParam("userId_2") String userId) {
        //User user = userRepository.findByUserId(userId).orElse(null);
        //System.out.println("passed userid is: " + userId);
        System.out.println(userId);

        List<Ticket> allTickets = ticketRepository.findAll();
            if (allTickets != null && userId.equals("david123")) {
                System.out.println(allTickets);
                return allTickets;

//            } else if {allTickets != null){
//               user= UserRepository.findById(userId;
//                List<Ticket> tickets=TicketRepository.findByUser(user);
//                return tickets;
            }else{
            //return "redirect:/allTickets.html";
            System.out.println("User Id does not exist!");
            return null;
        }
    }
}
