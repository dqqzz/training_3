package com.example.revatureproject5.Controller;

import com.example.revatureproject5.JPA.TicketRepository;
import com.example.revatureproject5.JPA.UserRepository;
import com.example.revatureproject5.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AllUnresolvedTicketsController {
    @Autowired
    private TicketRepository ticketRepository;
    private  UserRepository user;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/allUnresolvedTickets")
    public List<Ticket> showUnresolvedTicketForm(@RequestParam("userId_2") String userId) {
        return ticketRepository.findAllByResolvedFalse();
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/allUnresolvedTickets")
//    public String userTickets(Ticket ticket, @RequestParam("userId") Long userId) {
    public List<Ticket> allUnresolvedickets(@RequestParam("userId_2") String userId) {
        //User user = userRepository.findByUserId(userId).orElse(null);
        //System.out.println("passed userid is: " + userId);
        System.out.println(userId);

        List<Ticket> allUnresolvedTickets = ticketRepository.findAllByResolvedFalse();
        if (allUnresolvedTickets != null && userId.equals("david123")) {
            System.out.println(allUnresolvedTickets);
            return allUnresolvedTickets;

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
