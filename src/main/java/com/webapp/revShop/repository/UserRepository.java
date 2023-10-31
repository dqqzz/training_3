package com.webapp.revShop.repository;

import com.webapp.revShop.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.user_email = :user_email")
    Users findByEmail(@Param("user_email") String user_email);
}
