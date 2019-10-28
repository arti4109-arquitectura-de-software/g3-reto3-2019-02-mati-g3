package com.holding.adapter.xml.service;


import java.util.concurrent.CompletableFuture;

public interface ConverterService {

    CompletableFuture<String> converterXmlToJson(String data) throws Exception ;
}
