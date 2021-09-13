package com.parlonsdev.controller;


import com.parlonsdev.dto.ProductDto;
import com.parlonsdev.dto.ResponseMessage;
import com.parlonsdev.model.Product;
import com.parlonsdev.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE A NEW PRODUCT
    @PostMapping(path = "/new")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto productDto){
        try {
            if (productService.existsByName(productDto.getName()))
                return new ResponseEntity<>(new ResponseMessage("Product ("+productDto.getName()+") is exist!"), HttpStatus.BAD_REQUEST);
            if (productDto.getName().isBlank() || productDto.getDescription().isBlank())
                return new ResponseEntity<>(new ResponseMessage("The field is required!"), HttpStatus.BAD_REQUEST);
            if (productDto.getStock()<0)
                return new ResponseEntity<>(new ResponseMessage("Le stock du produit est strictement > 0!"), HttpStatus.BAD_REQUEST);
            if (productDto.getPrice()==null || productDto.getPrice()<0)
                return new ResponseEntity<>(new ResponseMessage("Le prix doit strictement > 0!"), HttpStatus.BAD_REQUEST);

            Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getStock(),
                    productDto.getDescription(), productDto.getInStock());
            productService.save(product);
            return new ResponseEntity<>(new ResponseMessage("Product  ("+product.getName()+") is created successfully!"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET ALL PRODUCTS
    @GetMapping(path = "/all")
    public ResponseEntity<?> getAllProducts(){
       try {
           List<Product> products = productService.list();
           if (products.isEmpty())
               return new ResponseEntity<>(new ResponseMessage("The List is empty!"), HttpStatus.BAD_REQUEST);
           return new ResponseEntity<>(products, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    // GET A SINGLE PRODUCT BY ID
    @GetMapping(path = "/detail/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        try {
            if (!productService.existsById(id))
                return new ResponseEntity(new ResponseMessage("Product does not exist!"), HttpStatus.NOT_FOUND);
            Product product = productService.getOne(id).get();
            return new ResponseEntity(product, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET A SINGLE PRODUCT BY NAME
    @GetMapping(path = "/detailname/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable("name") String name){
        try {
            if (!productService.existsByName(name))
                return new ResponseEntity<>(new ResponseMessage("Product ("+name+") does not exists!"), HttpStatus.NOT_FOUND);
            Product product = productService.getByName(name).get();
            return new ResponseEntity<>(product, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE A SINGLE PRODUCT BY ID
    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto){
        try {
            if (!productService.existsById(id))
                return new ResponseEntity<>(new ResponseMessage("Product does not exist!"), HttpStatus.NOT_FOUND);
            if (productService.existsByName(productDto.getName()) &&
                    productService.getByName(productDto.getName()).get().getId() !=id)
                return new ResponseEntity<>(new ResponseMessage("The product ("+productDto.getName()+") is exist!"), HttpStatus.BAD_REQUEST);
            if (productDto.getName().isBlank() || productDto.getDescription().isBlank())
                return new ResponseEntity<>(new ResponseMessage("The field is required!"), HttpStatus.BAD_REQUEST);
            if (productDto.getStock()<0)
                return new ResponseEntity<>(new ResponseMessage("Le stock du produit est strictement > 0!"), HttpStatus.BAD_REQUEST);
            if (productDto.getPrice()==null || productDto.getPrice()<0)
                return new ResponseEntity<>(new ResponseMessage("Le prix doit strictement > 0!"), HttpStatus.BAD_REQUEST);

            Product product = productService.getOne(id).get();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setStock(productDto.getStock());
            product.setDescription(productDto.getDescription());
            product.setInStock(productDto.getInStock());
            productService.save(product);
            return new ResponseEntity<>(new ResponseMessage("Product ("+product.getName()+") has been updated successfully!"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE A SINGLE PRODUCT BY ID
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id){
        try {
            if (!productService.existsById(id))
                return new ResponseEntity<>(new ResponseMessage("Product does not exist!"), HttpStatus.NOT_FOUND);
            productService.delete(id);
            return new ResponseEntity<>(new ResponseMessage("Product "+id+" has been deleted successfully!"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE ALL PRODUCTS
    @DeleteMapping(path = "/delete/all")
    public ResponseEntity<?> deleteAllProducts(){
        try {
            productService.deleteAll();
            return new ResponseEntity<>(new ResponseMessage("The all products have been deleted successfully!"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseMessage("ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
