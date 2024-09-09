package com.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableEurekaServer
//This class is the main class which stores all the information about our MS like how many ms we have
public class EurekaServerServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerServiceRegistryApplication.class, args);
	}

}
