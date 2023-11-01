package com.example.revatureproject5.JPA;

import com.example.revatureproject5.model.Ticket;
import com.example.revatureproject5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAll();
    List<Ticket> findByResolved(boolean resolved);
    List<Ticket> findByUser(User user);
    List<Ticket> findAllByResolvedFalse();
}
