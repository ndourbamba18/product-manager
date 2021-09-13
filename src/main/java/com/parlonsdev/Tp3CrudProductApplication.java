package com.parlonsdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Tp3CrudProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp3CrudProductApplication.class, args);
	}

}
