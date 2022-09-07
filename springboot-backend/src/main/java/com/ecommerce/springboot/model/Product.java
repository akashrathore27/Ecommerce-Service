package com.ecommerce.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    private String productName;
    private String productCategory;
    private int price;
    private String brand;
    private String description;
    private String image;
    private Date postedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Customer customer;





    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<FeedBack> feedbacks=new HashSet<>();








}
