package com.holding.adapter.yaml.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.holding.adapter.yaml.service.ConverterService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class ConverterServiceImpl implements ConverterService {

    @Override
    @Async
    public CompletableFuture<String> converterYamlToJson(String yaml) throws Exception {

        System.out.println("converterYamlToJson Executing thread name - " + Thread.currentThread().getName());

        String response = "";
        try {
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(yaml, Object.class);
            ObjectMapper jsonWriter = new ObjectMapper();
            response =  jsonWriter.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return CompletableFuture.completedFuture(response);
    }
}
