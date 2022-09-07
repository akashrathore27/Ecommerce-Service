package com.ecommerce.springboot.services;

import com.ecommerce.springboot.payloads.ProductDto;
import com.ecommerce.springboot.payloads.ProductResponse;

import java.util.List;


public interface ProductService
{


      //create

    ProductDto createProduct(ProductDto productDto,Long ownerId,Long categoryId);

   //update

    ProductDto updateProduct(ProductDto productDto,Long productId);

    //delete
    void deleteProduct(Long productId);

    //get all product

    ProductResponse getAllProduct(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single Product

    ProductDto getProductById(Long productId);

    //get all product by category

    List<ProductDto> getProductByCategory(Long categoryId);

    //get all product by owner

    List<ProductDto>getProductByOwner(Long ownerId);

    //search product

    List<ProductDto>searchProduct(String keyword);

    //

}
