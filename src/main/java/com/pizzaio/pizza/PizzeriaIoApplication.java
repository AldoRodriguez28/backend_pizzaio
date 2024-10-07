package com.pizzaio.pizza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class PizzeriaIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaIoApplication.class, args);
	}

}
