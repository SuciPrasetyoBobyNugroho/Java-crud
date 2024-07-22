package com.example.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Page<Category> findByNameContains(String name, Pageable pageable);

}
