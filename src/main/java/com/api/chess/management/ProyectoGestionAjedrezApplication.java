package com.api.chess.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// TODO: Auto-generated Javadoc
/**
 * The Class ProyectoGestionAjedrezApplication.
 */
@EnableEurekaClient
@SpringBootApplication
public class ProyectoGestionAjedrezApplication{

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProyectoGestionAjedrezApplication.class, args);
	}
}
