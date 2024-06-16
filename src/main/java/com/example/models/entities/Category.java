package com.example.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_categories")
public class Category implements Serializable {

    @Id // Anotasi @Id digunakan untuk menandai bahwa atribut yang mengikuti anotasi ini
        // adalah primary key dari tabel yang terkait dalam basis data
    @GeneratedValue(strategy = GenerationType.IDENTITY) // digunakan untuk menentukan cara atau strategi bagaimana nilai
                                                        // dari primary key akan di-generate atau di-generate secara
                                                        // otomatis oleh sistem basis data.
    private Long id;

    @Column(length = 100, nullable = false, unique = true) // digunakan untuk mengkonfigurasi properti dari kolom yang
                                                           // terkait dengan atribut dalam tabel database
    private String name;

    // buat setter and getter
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
}
