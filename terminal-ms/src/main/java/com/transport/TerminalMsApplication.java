package com.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//Now I want to register my MS in ServiceRegistry for tracebility
@EnableDiscoveryClient
public class TerminalMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TerminalMsApplication.class, args);
	}

}
