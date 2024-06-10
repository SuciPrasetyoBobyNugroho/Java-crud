package com.example.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Product;
import java.util.List;


public interface ProductRepo extends CrudRepository<Product, Long>{

    List<Product> findByNameContains(String name);
    
}
