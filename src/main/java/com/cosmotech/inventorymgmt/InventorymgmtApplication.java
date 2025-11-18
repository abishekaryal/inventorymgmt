package com.cosmotech.inventorymgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing
@SpringBootApplication
@EnableAsync
public class InventorymgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorymgmtApplication.class, args);
	}

}
