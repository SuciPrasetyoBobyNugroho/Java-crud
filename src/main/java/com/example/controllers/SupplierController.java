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

import com.example.dto.ResponseData;
import com.example.dto.SupplierDto;
import com.example.models.entities.Supplier;
import com.example.services.SupplierService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> createSupplier(@Valid @RequestBody SupplierDto supplierDto,
            Errors errors) {

        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // mapping dengan cara manual
        // Supplier supplier = new Supplier();
        // supplier.setName(supplierDto.getName());
        // supplier.setAddress(supplierDto.getAddress());
        // supplier.setEmail(supplier.getEmail());

        // mapping dengan library/dependencies model mapper
        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.createSupplier(supplier));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAllSupplier() {
        return supplierService.findAllSupplier();
    }

    @GetMapping("/{id}")
    public Supplier findOneSupplier(@PathVariable("id") Long id) {
        return supplierService.findOneSupplier(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> updateSupplier(@Valid @RequestBody SupplierDto supplierDto,
            Errors errors) {

        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.createSupplier(supplier));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOneSupplier(@PathVariable("id") Long id) {
        supplierService.removeOneSupplier(id);
    }
    
}
