package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApiApplication.class, args);
	}

	//agar dependencies model mapper bisa digunakan/di inject di controller atau service dan file lainnya
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
