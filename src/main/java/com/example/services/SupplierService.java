package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.entities.Supplier;
import com.example.models.repository.SupplierRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier findOneSupplier(Long id) {
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if (!supplier.isPresent()) {
            return null;
        }
        return supplier.get();
    }

    public Iterable<Supplier> findAllSupplier() {
        return supplierRepo.findAll();
    }

    public void removeOneSupplier(Long id) {
        supplierRepo.deleteById(id);
    }

    public Supplier findByEmail(String email) {
        return supplierRepo.findByEmail(email);
    }

    public List<Supplier> findByNameContains(String name) {
        return supplierRepo.findByNameContains(name);
    }

    public List<Supplier> findByNameStartingWith(String name) {
        return supplierRepo.findByNameStartingWith(name);
    }

    public List<Supplier> findByNameOrEmail(String name, String email) {
        return supplierRepo.findByNameContainsOrEmailContains(name, email);
    }

    public List<Supplier> findByNameOrderByIdDesc(String name) {
        return supplierRepo.findByNameContainsOrderByIdDesc(name);
    }
}
