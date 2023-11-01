package com.example.revatureproject5.JPA;

import com.example.revatureproject5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByUserId(Long userId);
    User findByUserName(String userName);
    User save(User user);
    void deleteById(Long userId);



}
