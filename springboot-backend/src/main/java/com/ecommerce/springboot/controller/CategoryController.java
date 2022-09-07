package com.ecommerce.springboot.controller;

import com.ecommerce.springboot.payloads.ApiResponse;
import com.ecommerce.springboot.payloads.CategoryDto;
import com.ecommerce.springboot.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/v1/")
public class CategoryController
{



    @Autowired

    private CategoryService categoryService;


    //Create

    @PostMapping("/categories")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }


    //update

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Long categoryId) {

        CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<CategoryDto>(updatedCategoryDto, HttpStatus.OK);

    }

    //delete

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("category Deleted Successfully", true), HttpStatus.OK);
    }



    //get by id

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        CategoryDto categoryDto=this.categoryService.getCategoryById(categoryId);


        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }
    //get all

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categoryDto=this.categoryService.getAllCategory();
       return  ResponseEntity.ok(categoryDto);
    }


//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    // get all categories
//    @GetMapping("/categories")
//    public List<Category> getAllCategories(){
//        return categoryRepository.findAll();
//    }
//
//    // create category rest api
//    @PostMapping("/categories")
//    public Category createCategory(@RequestBody Category category)
//    {
//        return categoryRepository.save(category);
//    }
//
//    // get category by id rest api
//    @GetMapping("/categories/{id}")
//    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
//        return ResponseEntity.ok(category);
//    }
//
//    // update category rest api
//
//    @PutMapping("/categories/{id}")
//    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails){
//        Category category = categoryRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
//
//
//        category.setCategoryName(categoryDetails.getCategoryName());
//        category.setCategoryImage(categoryDetails.getCategoryImage());
//
//
//
//        Category updatedCategory = categoryRepository.save(category);
//        return ResponseEntity.ok(updatedCategory);
//    }
//
//    // delete category rest api
//    @DeleteMapping("/categories/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteCategory(@PathVariable Long id){
//        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not exist with id :" + id));
//
//        categoryRepository.delete(category);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }

}
