package com.ecommerce.springboot.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto
{
    private long categoryId;
    private String categoryName;
    private String categoryImage;
}
