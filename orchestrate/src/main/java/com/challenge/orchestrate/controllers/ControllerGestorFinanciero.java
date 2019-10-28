package com.challenge.orchestrate.controllers;

import java.io.StringReader;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

@RestController
@RefreshScope
public class ControllerGestorFinanciero {

	@Value("${external.endpoint.format}")
	private String urlAdapter;

	private String url;
	private boolean formatoValido;

	@PostMapping(path = "/RegistrarInformacion", produces = "application/json")
	public String registrarInformacion(String data) {

		String idCompany = null, formato = null, body = null;
		try {
			
			Gson gson = new Gson();
			JsonReader reader = new JsonReader(new StringReader(data));
			reader.setLenient(true);
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			
			idCompany = jsonObject.get("idCompany").getAsString();
			formato = jsonObject.get("format").getAsString();
			body = jsonObject.get("body").getAsString();

			
			reader = new JsonReader(new StringReader(urlAdapter));
			reader.setLenient(true);
			jsonObject = gson.fromJson(reader, JsonObject.class);
			JsonArray documents = (JsonArray) jsonObject.get("company");
			Iterator<JsonElement> itr = documents.iterator();
			formatoValido = false;
			while (itr.hasNext()) {
				JsonObject jsonO = (JsonObject) itr.next();
				if (jsonO.get("id").getAsString().equalsIgnoreCase(idCompany)) {

					JsonArray formats = (JsonArray) jsonO.get("format-enabled");
					Iterator<JsonElement> itrFormats = formats.iterator();
					while (itrFormats.hasNext()) {
						JsonPrimitive jsonForm = (JsonPrimitive) itrFormats.next();
						if (jsonForm.getAsString().contains(formato)) {
							formatoValido = true;
							url = jsonForm.getAsString();
						}
					}
				}
			}

			if (formatoValido) {

				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
				map.add("data", body);
				HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map,
						headers);
				ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
				formato = response.getBody();
			} else {
				formato = "Formato no válido";
			}

		} catch (Exception err) {

			err.printStackTrace();
		}

		return formato;
	}

}