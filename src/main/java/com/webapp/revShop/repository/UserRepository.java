package com.webapp.revShop.repository;

import com.webapp.revShop.entity.Users;
import com.webapp.revShop.wrapper.UserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.user_email = :user_email")
    Users findByEmail(@Param("user_email") String user_email);

    @Query("SELECT new com.webapp.revShop.wrapper.UserWrapper(u.user_id, u.user_name, u.user_email, u.user_role, u.user_status) FROM Users u WHERE u.user_role = 'buyer' OR u.user_role = 'seller'")
    List<UserWrapper> getAllUsers();

    @javax.transaction.Transactional
    @Modifying
    @Query("UPDATE Users u SET u.user_status = :user_status WHERE u.user_id = :user_id")
    Integer updateStatus(@Param("user_status") String user_status, @Param("user_id") Integer user_id);
}
