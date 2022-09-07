package com.ecommerce.springboot.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class ProductDto
{
    private long productId;
    private String productName;
    private String productCategory;
    private int price;
    private String brand;
    private String description;
    private String image;
    private Date postedDate;


    private CategoryDto category;

    private OwnerDto owner;

    private Set<FeedbackDto> feedBacks=new HashSet<>();




}
