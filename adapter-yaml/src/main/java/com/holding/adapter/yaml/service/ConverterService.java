package com.holding.adapter.yaml.service;


import java.util.concurrent.CompletableFuture;

public interface ConverterService {

    CompletableFuture<String> converterYamlToJson(String data) throws Exception ;
}
