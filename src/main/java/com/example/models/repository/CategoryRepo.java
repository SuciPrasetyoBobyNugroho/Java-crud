package com.example.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Category;

public interface CategoryRepo extends CrudRepository<Category, Long> {

}
