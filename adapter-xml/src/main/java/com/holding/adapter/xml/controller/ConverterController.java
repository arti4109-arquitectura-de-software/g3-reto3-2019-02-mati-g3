package com.holding.adapter.xml.controller;

import com.holding.adapter.xml.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/api")
@RestController
public class ConverterController {

    @Autowired
    private ConverterService converterService;

    @RequestMapping(value = "/adapter/xml/converter", method = RequestMethod.POST)
    public ResponseEntity<String> createBand(@RequestBody String data) throws Exception {

        CompletableFuture<String> result = converterService.converterXmlToJson(data);

        // Wait until they are all done
        CompletableFuture.allOf(result).join();

        return ResponseEntity.ok(result.get());
    }

}
