package com.webapp.revShop.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

//com.webapp.revShop.entity.Bill
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "bill")
public class Bill implements Serializable {

    private static final long serialVersionBillID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bill_id;

    private double bill_total;

    private String bill_payment_method;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
