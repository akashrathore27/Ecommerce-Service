package com.ecommerce.springboot.repository;

import com.ecommerce.springboot.model.Category;
import com.ecommerce.springboot.model.Owner;
import com.ecommerce.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>
{

    List<Product> findByOwner(Owner owner);
    List<Product> findByCategory(Category category);

    List<Product> findByProductCategoryContaining(String productCategory);


}
