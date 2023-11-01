package com.example.revatureproject5.model;
import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketId")
    private Long ticketId;
    @Column(name = "description")
    private String description;
    @Column(name = "resolved")
    private boolean resolved;
    public Ticket() {

    }

    public Ticket(Long ticketId, String description, boolean resolved, User user) {
        this.ticketId = ticketId;
        this.description = description;
        this.resolved = resolved;
        this.user = user;
    }
    public Ticket(String description, boolean resolved, User user) {
        this.description = description;
        this.resolved = resolved;
        this.user = user;
    }
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long id) {
        this.ticketId = ticketId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void addAttribute(String ticket, Ticket ticket1) {
    }
// Constructors, getters, and setters

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", description='" + description + '\'' +
                ", resolved=" + resolved +
                ", user=" + user +
                '}';
    }
}
