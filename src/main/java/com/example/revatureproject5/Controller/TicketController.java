package com.example.revatureproject5.Controller;

import com.example.revatureproject5.JPA.TicketRepository;
import com.example.revatureproject5.JPA.UserRepository;
import com.example.revatureproject5.model.Ticket;
import com.example.revatureproject5.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
Ticket ticket;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;

//    @CrossOrigin(origins = "http://localhost:3000")
//    @GetMapping("/tickets")
//    public String showTicketForm(Ticket ticket) {
//        //ticket.addAttribute("ticket", new Ticket());
//        return "NewTicket";
//    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/tickets")
    public Ticket addNewTicket(@RequestParam("description") String description, @RequestParam("resolved") boolean resolved,@RequestParam("userId") Long userId) {
        // 从数据库中查找对应的用户
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            // 关联用户与Ticket
            System.out.println(user);
            System.out.println("user name is: "+ user.getPassword());
            ticket=new Ticket(description, resolved, user);
            ticketRepository.save(ticket);
            return ticket;
        } else {
            // 处理用户不存在的情况
            // 可以在这里返回错误信息或采取其他操作
            System.out.println("no such user id");
            return null;
        }

       // return "redirect:/allTickets.html?&description=" + ticket.getDescription() + "&resolved=" + ticket.isResolved() + "&userId=" + user.getUserId();

    }


    }
