package com.api.chess.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProyectoGestionAjedrezApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ProyectoGestionAjedrezApplication.class, args);
	}

}
