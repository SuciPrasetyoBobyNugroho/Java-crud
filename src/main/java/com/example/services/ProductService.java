package com.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.models.entities.Product;
import com.example.models.entities.Supplier;
import com.example.models.repository.ProductRepo;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    public Product create(Product product) {
        return productRepo.save(product);
    }

    public Product findOne(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()) {
            return null;
        }
        return product.get();
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    public void removeOne(Long id) {
        productRepo.deleteById(id);
    }

    // menambahkan data dengan relasi antar entity product dan supplier (menambahkan
    // data supplier ke product)

    public void addSupplier(Supplier supplier, Long productId) {
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product with ID : " + productId + " not found");
        }
        product.getSuppliers().add(supplier);
        create(product);
    }

    // mencari nama product dengan nama lengkap
    // mencari 1 data dengan nama lengkap
    public Product findProductName(String name) {
        return productRepo.findProductByName(name);
    }

    // mencari nama product yang mengandung nama yg di cari (tidak perlu nama
    // lengkap)
    // mencari lebih dari 1 data yang mengandung nama yg di cari
    public List<Product> findProductNameLike(String name) {
        return productRepo.findProductByNameLike("%" + name + "%");
    }

    // mencari data product dengan keyword id category
    public List<Product> findProductByCategory(Long categoryId) {
        return productRepo.findProductByCategory(categoryId);
    }

    // mencari data product berdasarkan supplier
    public List<Product> findProductBySupplier(Long supplierId) {
        Supplier supplier = supplierService.findOneSupplier(supplierId); // mencari supplier berdasarkan id
        if (supplier == null) {
            return new ArrayList<Product>();
        }
        return productRepo.findProductBySupplier(supplier); // mengembalikan daftar produk yang terkait dengan supplier
                                                             // yang diberikan
    }

}
