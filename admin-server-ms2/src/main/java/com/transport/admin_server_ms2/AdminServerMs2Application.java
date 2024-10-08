package com.transport.admin_server_ms2;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class AdminServerMs2Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerMs2Application.class, args);
	}

}
