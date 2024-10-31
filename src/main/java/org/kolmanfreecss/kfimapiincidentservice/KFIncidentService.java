package org.kolmanfreecss.kfimapiincidentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main class for the Spring Boot application.
 * 
 * @version 1.0
 * @author Kolman-Freecss
 */
@SpringBootApplication
@EnableCaching
public class KFIncidentService {

	public static void main(String[] args) {
		SpringApplication.run(KFIncidentService.class, args);
	}

}
