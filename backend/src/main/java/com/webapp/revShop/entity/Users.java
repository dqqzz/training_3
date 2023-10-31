package com.webapp.revShop.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

//com.webapp.revShop.entity.Users
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "users")
public class Users implements Serializable {

    private static final long serialVersionUserID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    private String user_name;

    private String user_email;

    private String user_password;

    private String user_role;

    private String user_status;

//    public  enum User_role{
//        BUYER,
//        SELLER,
//        ADMIN
//}
//    private User_role user_role;
}
