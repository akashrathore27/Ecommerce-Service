package com.ecommerce.springboot.services;

import com.ecommerce.springboot.payloads.CategoryDto;

import java.util.List;

public interface CategoryService
{
    //create


    public CategoryDto createCategory(CategoryDto categoryDto);


    //update
    public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

    //delete

    public void deleteCategory(Long categoryId);

    //get by id

    public CategoryDto getCategoryById(Long categoryId);



    //get all
    List<CategoryDto>  getAllCategory();
}
