package com.example.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

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

import com.example.dto.ResponseData;
import com.example.models.entities.Product;
import com.example.models.entities.Supplier;
import com.example.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        try {
            responseData.setStatus(true);
            responseData.setPayload(productService.create(product));
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        try {
            Iterable<Product> products = productService.findAll();
            if (products.iterator().hasNext()) { // Metode ini memeriksa apakah ada elemen di dalam Iterable. Jika ada,
                                                 // maka iterator().hasNext() akan mengembalikan true.
                map.put("status", 1); // Menunjukkan bahwa operasi berhasil
                map.put("Data", products);
                return new ResponseEntity<>(map, HttpStatus.OK);
            } else {
                map.clear();
                map.put("status", 0); // Menunjukkan bahwa data tidak ditemukan atau operasi gagal
                map.put("message", "data tidak di temukan");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.clear();
            map.put("status", 0);
            map.put("message", "internal server error");
            map.put("error", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        try {
            Product products = productService.findOne(id);
            if (products != null) {
                map.put("status", 1);
                map.put("Data", products);
                return new ResponseEntity<>(map, HttpStatus.OK);
            } else {
                map.clear();
                map.put("status", 0);
                map.put("message", "Data dengan id : " + id + " tidak di temukan");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.clear();
            map.put("status", 0);
            map.put("message", "internal server error");
            map.put("error", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Product>> update(@PathVariable("id") Long id,
            @Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        try {
            Product existingProduct = productService.findOne(id);
            if (existingProduct == null) {
                responseData.setStatus(false);
                responseData.getMessages().add("product not found");
                responseData.setPayload(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
            }

            // Gunakan ModelMapper untuk menyalin nilai dari 'product' ke 'existingProduct'
            modelMapper.map(product, existingProduct);

            // Pastikan ID produk tetap sama dengan ID yang diambil dari path variable
            existingProduct.setId(id);

            Product updaProduct = productService.create(existingProduct);

            responseData.setStatus(true);
            responseData.setPayload(updaProduct);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();

        try {
            Product products = productService.findOne(id);
            productService.removeOne(id);
            if (products != null) {
                map.put("status", 1);
                map.put("message", "Data dengan id : " + id + " berhasil di hapus");
                return new ResponseEntity<>(map, HttpStatus.OK);
            } else {
                map.clear();
                map.put("status", 0);
                map.put("message", "Data dengan id : " + id + " tidak ditemukan");
                return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            map.clear();
            map.put("status", 0);
            map.put("message", "internal server error");
            map.put("error", e.getMessage());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // menambahkan data dengan relasi antar entity product dan supplier (menambahkan
    // data supplier ke product)
    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId) {
        productService.addSupplier(supplier, productId);
    }

}
