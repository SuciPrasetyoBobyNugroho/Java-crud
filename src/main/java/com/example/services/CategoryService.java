package com.example.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.models.entities.Category;
import com.example.models.repository.CategoryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

    @Autowired // untuk menyuntikkan (inject) dependensi secara otomatis ke dalam kelas yang
               // memerlukannya ( Penggunaan CategoryRepo di dalam metode CategoryService )
    private CategoryRepo categoryRepo;

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category findOneCategory(Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (!category.isPresent()) {
            return null;
        }
        return category.get();
    }

    public Iterable<Category> findAllCategory() {
        return categoryRepo.findAll();
    }

    public void removeOneCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    public Iterable<Category> findByNameContains(String name, Pageable pageable){
        return categoryRepo.findByNameContains(name, pageable);
    }

    public Iterable<Category> saveBatch(Iterable<Category> category){
        return categoryRepo.saveAll(category);
    }

}
