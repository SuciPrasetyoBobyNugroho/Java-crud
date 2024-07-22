package com.example.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Supplier;
import java.util.List;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

    Supplier findByEmail(String email);

    // Mencari semua user yang usernamenya mengandung substring yang diberikan.
    // contains sama dengan LIKE
    List<Supplier> findByNameContains(String name);

    List<Supplier> findByNameStartingWith(String name);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);
}
