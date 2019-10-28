package com.holding.adapter.xml.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.holding.adapter.xml.service.ConverterService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Service
public class ConverterServiceImpl implements ConverterService {

    @Override
    @Async
    public CompletableFuture<String> converterXmlToJson(String data) throws Exception {

        System.out.println("converterXmlToJson Executing thread name - " + Thread.currentThread().getName());

        String response = "";
        try
        {
            // Create a new XmlMapper to read XML tags
            XmlMapper xmlMapper = new XmlMapper();

            //Reading the XML
            JsonNode jsonNode = xmlMapper.readTree(data.getBytes());

            //Create a new ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            //Get JSON as a string
            response = objectMapper.writeValueAsString(jsonNode);

        } catch (IOException e){
            e.printStackTrace();
        }


        return CompletableFuture.completedFuture(response);
    }
}
