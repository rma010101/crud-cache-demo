package com.crudproj.mysecondcrud002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Mysecondcrud002Application {

	public static void main(String[] args) {
		SpringApplication.run(Mysecondcrud002Application.class, args);
	}

}
