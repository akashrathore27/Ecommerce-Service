package com.ecommerce.springboot.services.impl;

import com.ecommerce.springboot.repository.CategoryRepository;
import com.ecommerce.springboot.repository.ProductRepository;
import com.ecommerce.springboot.exception.ResourceNotFoundException;
import com.ecommerce.springboot.model.Category;
import com.ecommerce.springboot.model.Owner;
import com.ecommerce.springboot.model.Product;
import com.ecommerce.springboot.payloads.ProductDto;
import com.ecommerce.springboot.payloads.ProductResponse;
import com.ecommerce.springboot.repository.OwnerRepository;
import com.ecommerce.springboot.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl  implements ProductService
{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OwnerRepository ownerRepository;


    @Override
    public ProductDto createProduct(ProductDto productDto,Long ownerId,Long categoryId)
    {

        Owner owner=this.ownerRepository.findById(ownerId).orElseThrow(()->new ResourceNotFoundException("owner","owner Id",ownerId));

        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Category Id",categoryId));



        Product product=this.modelMapper.map(productDto,Product.class);
        product.setImage("default.png");
        product.setPostedDate(new Date());

        product.setOwner(owner);
        product.setCategory(category);

        Product newProduct=this.productRepository.save(product);

        return this.modelMapper.map(newProduct,ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {

        Product product=this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("product","product id",productId));

        product.setProductName(productDto.getProductName());
        product.setProductCategory(productDto.getProductCategory());
        product.setPrice(productDto.getPrice());
        product.setBrand(productDto.getBrand());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());

        Product updatedProduct=this.productRepository.save(product);

        return this.modelMapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void deleteProduct(Long productId) {

        Product product=this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("product","product id",productId));
        this.productRepository.delete(product);

    }

    @Override
    public ProductResponse getAllProduct(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc"))
        {
            sort=Sort.by(sortBy).ascending();
        }
        else
        {
            sort=Sort.by(sortBy).descending();
        }


        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> pageProduct=this.productRepository.findAll(pageable);

        List<Product>allProducts=pageProduct.getContent();


        List<ProductDto>productDtos=allProducts.stream().map((product)->this.modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());

        ProductResponse productResponse=new ProductResponse();
        productResponse.setProduct(productDtos);
        productResponse.setPageNumber(pageProduct.getNumber());
        productResponse.setPageSize(pageProduct.getSize());
        productResponse.setTotalElements(pageProduct.getNumberOfElements());
        productResponse.setTotalPages(pageProduct.getTotalPages());
        productResponse.setLastPage(productResponse.isLastPage());


        return productResponse;
    }

    @Override
    public ProductDto getProductById(Long productId) {

        Product product=this.productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("product","product Id",productId));
        return this.modelMapper.map(product,ProductDto.class);
    }

    @Override
    public List<ProductDto> getProductByCategory(Long categoryId) {

        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category Id",categoryId));

        List<Product>products=this.productRepository.findByCategory(category);
        List<ProductDto>productDtos=products.stream().map(product -> this.modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public List<ProductDto> getProductByOwner(Long ownerId) {

        Owner owner=this.ownerRepository.findById(ownerId).orElseThrow(()->new ResourceNotFoundException("owner","owner id",ownerId));

        List<Product>products=this.productRepository.findByOwner(owner);

        List<ProductDto>productDtos=products.stream().map(product -> this.modelMapper.map(product,ProductDto.class)).collect(Collectors.toList());

        return productDtos;
    }

    @Override
    public List<ProductDto> searchProduct(String keyword) {

        List<Product>products=this.productRepository.findByProductCategoryContaining(keyword);

        List<ProductDto>productDtos=products.stream().map((product -> this.modelMapper.map(product,ProductDto.class))).collect(Collectors.toList());

        return productDtos;
    }
}
