package com.challenge.orchestrate.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@RestController
public class ControllerGestorFinanciero {

    @Value("${external.endpoint.format}")
    private String urlAdapter;

    @PostConstruct
    void setup(){

        System.out.print("urlAdapter");
    }

    @PostMapping(path = "/RegistrarInformacion", produces = "application/json")
    public String registrarInformacion(String data) {

      return urlAdapter;
    }
}
