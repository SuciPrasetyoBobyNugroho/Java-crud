package com.example.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CategoryDto;
import com.example.dto.ResponseData;
import com.example.models.entities.Category;
import com.example.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> createCategory(@Valid @RequestBody CategoryDto categoryDto,
            Errors errors) {

        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        responseData.setStatus(true);

        responseData.setPayload(categoryService.createCategory(category));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Category> findAllCategory() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    public Category findOneCategory(@PathVariable("id") Long id) {
        return categoryService.findOneCategory(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> updateCategory(@PathVariable("id") Long id,
            @Valid @RequestBody CategoryDto categoryDto,
            Errors errors) {

        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category existingCategory = categoryService.findOneCategory(id);
        if (existingCategory == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Category not found");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        // mapping dengan library/dependencies model mapper
        modelMapper.map(categoryDto, existingCategory);

        // setelah id di temukan data akan di update (service sama dengan create data/POST)
        Category updatedCategory = categoryService.createCategory(existingCategory);

        responseData.setStatus(true);
        responseData.setPayload(updatedCategory);
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOneCategory(@PathVariable("id") Long id) {
        categoryService.removeOneCategory(id);
    }
}
