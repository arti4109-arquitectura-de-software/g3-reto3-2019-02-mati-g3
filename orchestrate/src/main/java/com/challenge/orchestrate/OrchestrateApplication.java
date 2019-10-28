package com.challenge.orchestrate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OrchestrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrchestrateApplication.class, args);


	}


}