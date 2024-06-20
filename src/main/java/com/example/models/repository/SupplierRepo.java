package com.example.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {

}
