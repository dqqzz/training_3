package com.example.revatureproject5.model;

import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @Column(name = "username", unique = true) // Set unique to true
    private String userName;
    @Column(name = "password", unique = true)
    private String password;
    public User() {

    }
//    public User(Long userId, String userName, String password) {
//        this.userId = userId;
//        this.userName = userName;
//        this.password = password;
//    }
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addAttribute(String user, User user1) {
    }
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
// getters, setters, constructors
}
