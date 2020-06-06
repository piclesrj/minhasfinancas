package com.prodrigues.minhasfinancas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinhasfinancasApplication {
	
	
	

	public static void main(String[] args) {
	//SpringApplication.run(MinhasfinancasApplication.class, args);
		SpringApplication application = new SpringApplication(MinhasfinancasApplication.class);
		   // ... customize application settings here
		   application.run(args);
	}

}
