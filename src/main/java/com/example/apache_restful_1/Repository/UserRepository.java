package com.example.apache_restful_1.Repository;

import com.example.apache_restful_1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
