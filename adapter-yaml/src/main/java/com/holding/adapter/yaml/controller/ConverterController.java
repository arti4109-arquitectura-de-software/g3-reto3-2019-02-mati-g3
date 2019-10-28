package com.holding.adapter.yaml.controller;


import com.holding.adapter.yaml.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ConverterController {

    @Autowired
    private ConverterService converterService;

    @RequestMapping(value = "/adapter/xml/converter", method = RequestMethod.POST)
    public ResponseEntity<String> createBand(@RequestBody String data) throws Exception {
        return ResponseEntity.ok(converterService.converterYamlToJson(data));
    }

}
