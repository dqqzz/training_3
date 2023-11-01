package com.example.revatureproject5.JPA;

import com.example.revatureproject5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long userId);
    User findByUserName(String userName);

}

