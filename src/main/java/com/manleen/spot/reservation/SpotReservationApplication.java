package com.manleen.spot.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.manleen")
@SpringBootApplication
public class SpotReservationApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpotReservationApplication.class, args);
	}
}
