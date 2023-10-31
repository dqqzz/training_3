package com.webapp.revShop.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

//com.webapp.revShop.entity.Product
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "products")
public class Product implements Serializable {

    public static final Long serialVersionProductID = 123456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer product_id;

    private String product_imageURL;

    private double product_price;

    private String product_description;

//    private double product_avgRating;    Not yet sure if we want to add this.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String product_status;

}
