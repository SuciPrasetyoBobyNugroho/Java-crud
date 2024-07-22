package com.example.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {


    private Long id;

    @NotEmpty(message = "name is required")
    private String name;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
