package com.holding.adapter.xml.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.holding.adapter.xml.service.ConverterService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConverterServiceImpl implements ConverterService {

    @Override
    public String converterXmlToJson(String data) throws Exception {

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

            System.out.println("*** Converting XML to JSON ***");
            System.out.println(response);


        } catch (JsonParseException e)
        {
            e.printStackTrace();
        } catch (JsonMappingException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        return response;
    }
}
