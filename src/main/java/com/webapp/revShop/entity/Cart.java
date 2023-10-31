package com.webapp.revShop.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

//com.webapp.revShop.entity.Cart
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "cart")
public class Cart {

    private static final Long serialVersionbCartID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cart_id;

    private Integer user_id;



}
