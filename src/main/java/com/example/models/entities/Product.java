package com.example.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_name", length = 100)
    private String name;

    @Column(name="product_description", length =500)
    private String description;

    private BigDecimal price;

    public Product() {
    }

    // JIKA MENGGUNAKAN DEPENDENCIES LOMBOK TIDAK PERLU MEMBUAT CONSTRUCTOR DAN SETTER GETTER

    // membuat constructor = klik kanan -> source action -> generate constructor -> ceklis field 
    // yang akan di buat

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }


    // membuat setter getter = klik kanan -> source action -> generate setter getter -> ceklis field 
    // yang akan di buat
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
}
