package com.parlonsdev.service;

import com.parlonsdev.model.Product;
import com.parlonsdev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> list(){
        return productRepository.findAll();
    }

    public Optional<Product> getOne(Long id){
        return productRepository.findById(id);
    }

    public Optional<Product> getByName(String name){
        return productRepository.findByName(name);
    }

    public void  save(Product product){
        productRepository.save(product);
    }

    public void  delete(Long id){
        productRepository.deleteById(id);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }

    public Boolean existsById(Long id){
        return productRepository.existsById(id);
    }

    public Boolean existsByName(String name){
        return productRepository.existsByName(name);
    }


}
