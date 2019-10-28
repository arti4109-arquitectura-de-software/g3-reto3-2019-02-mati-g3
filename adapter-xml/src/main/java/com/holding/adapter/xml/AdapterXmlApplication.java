package com.holding.adapter.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AdapterXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdapterXmlApplication.class, args);
	}

}
