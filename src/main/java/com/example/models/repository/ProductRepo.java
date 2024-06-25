package com.example.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.models.entities.Product;
import com.example.models.entities.Supplier;

import jakarta.websocket.server.PathParam;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    // Anotasi ini digunakan untuk mendefinisikan custom query menggunakan JPQL
    @Query("SELECT p FROM Product p WHERE p.name = :name") // mencari 1 data dengan nama lengkap
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.name LIKE :name") // mencari lebih dari 1 data yang mengandung nama yg di
                                                              // cari
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId") // mencari data product dengan keyword id
                                                                        // category
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    // mencari data product berdasarkan supplier
    // MEMBER OF: Operator ini digunakan di JPQL untuk memeriksa apakah sebuah
    // elemen merupakan anggota dari koleksi.
    // p.suppliers: Ini adalah koleksi suppliers dalam entitas Product.
    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
}
