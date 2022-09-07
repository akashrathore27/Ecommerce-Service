package com.ecommerce.springboot.controller;

import com.ecommerce.springboot.config.AppConstants;
import com.ecommerce.springboot.payloads.ApiResponse;
import com.ecommerce.springboot.payloads.ProductDto;
import com.ecommerce.springboot.payloads.ProductResponse;
import com.ecommerce.springboot.services.FileService;
import com.ecommerce.springboot.services.ProductService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class ProductController
{


    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;



    //create post
    @PostMapping("/owner/{ownerId}/category/{categoryId}/products")
    public ResponseEntity<ProductDto>createProduct(@RequestBody ProductDto productDto, @PathVariable Long ownerId, @PathVariable Long categoryId )
    {
        ProductDto createProduct=this.productService.createProduct(productDto,ownerId,categoryId);
        return  new ResponseEntity<ProductDto>(createProduct,HttpStatus.CREATED);
    }

    //get by owner

    @GetMapping("/owner/{ownerId}/products")
    public ResponseEntity<List<ProductDto>> getProductByOwner (@PathVariable Long ownerId)
    {
        List<ProductDto>productDtos=this.productService.getProductByOwner(ownerId);
        return  new ResponseEntity<List<ProductDto>>(productDtos,HttpStatus.OK);
    }

    //get product by category
    @GetMapping("/category/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductByCategory (@PathVariable Long categoryId)
    {
        List<ProductDto>productDtos=this.productService.getProductByCategory(categoryId);
        return  new ResponseEntity<List<ProductDto>>(productDtos,HttpStatus.OK);
    }


    //get All product
    @GetMapping("/products")
    public ResponseEntity<ProductResponse> getAllProduct(@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)  Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize , @RequestParam(value="sortBy",defaultValue=AppConstants.SORT_BY,required = false)String sortBy, @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir)
    {
        ProductResponse productResponse=this.productService.getAllProduct(pageNumber,pageSize,sortBy,sortDir);

        return  new ResponseEntity<ProductResponse>(productResponse,HttpStatus.OK);
    }


    //get product by Id

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId)
    {
        ProductDto productDto = this.productService.getProductById(productId);
         return  new ResponseEntity<ProductDto>(productDto,HttpStatus.OK);
    }

    //delete product
    @DeleteMapping("/products/{productId}")
    public ApiResponse deleteProduct(@PathVariable Long productId)
    {
        this.productService.deleteProduct(productId);
        return new ApiResponse("product deleted sucessfully",true);
    }

    //update product
@PutMapping("/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable Long productId)
    {
       ProductDto updateProduct= this.productService.updateProduct(productDto,productId);
        return  new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
    }



    //search product
    @GetMapping("/products/search/{keywords}")
    public ResponseEntity<List<ProductDto>> searchProductByCategory(@PathVariable("keywords") String keywords)
    {
      List<ProductDto>result=this.productService.searchProduct(keywords);
      return  new ResponseEntity<List<ProductDto>>(result,HttpStatus.OK);
    }

    // product image upload
    @PostMapping("/product/image/upload/{productId}")
    public ResponseEntity<ProductDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                      @PathVariable Long productId) throws IOException {
        ProductDto productDto = this.productService.getProductById(productId);
        String fileName = this.fileService.UploadImage(path, image);

        productDto.setImage(fileName);

        ProductDto updateProduct=	this.productService.updateProduct(productDto, productId);
        return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
    }

    // shows image file

    @GetMapping(value="/product/image/{imageName}", produces= MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response

    ) throws IOException {

        InputStream resource =this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }

//
//    @Autowired
//
//    private ProductRepository productRepository;
//    // get all products
//    @GetMapping("/products")
//    public List<Product> getAllProducts(){
//        return productRepository.findAll();
//    }
//
//    // create products rest api
//    @PostMapping("/products")
//    public Product createProduct(@RequestBody Product product)
//    {
//        return productRepository.save(product);
//    }
//
//    // get product by id rest api
//    @GetMapping("/products/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
//        return ResponseEntity.ok(product);
//    }
//
//    // update product rest api
//
//    @PutMapping("/products/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
//        Product product= productRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("product not exist with id :" + id));
//
//
//
//        product.setProductName(productDetails.getProductName());
//        product.setProductCategory(productDetails.getProductCategory());
//        product.setPrice(productDetails.getPrice());
//        product.setBrand(productDetails.getBrand());
//        product.setImage(productDetails.getImage());
//        product.setDescription(productDetails.getDescription());
//
//
//        Product updatedProduct = productRepository.save(product);
//        return ResponseEntity.ok(updatedProduct);
//    }
//
//    // delete product rest api
//    @DeleteMapping("/products/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
//        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
//
//        productRepository.delete(product);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }

}

