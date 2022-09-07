package com.ecommerce.springboot.services.impl;

import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.payloads.CategoryDto;
import com.ecommerce.springboot.repository.CategoryRepository;
import com.ecommerce.springboot.services.CategoryService;
import com.ecommerce.springboot.model.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService
{

    @Autowired
    private CategoryService categoryService;

    @Autowired

    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(addedCategory, CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryImage(categoryDto.getCategoryImage());

        Category updatedCategory=this.categoryRepository.save(category);

        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));

        this.categoryRepository.delete(category);


    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));

        return this.modelMapper.map(category,CategoryDto.class);
           }



    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category>categories= this.categoryRepository.findAll();
        List<CategoryDto>categoryDtos=categories.stream().map((category) -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
