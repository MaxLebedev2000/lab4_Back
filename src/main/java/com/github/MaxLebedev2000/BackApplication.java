package com.github.MaxLebedev2000;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(BackApplication.class);
		app.run(args);
	}

}
