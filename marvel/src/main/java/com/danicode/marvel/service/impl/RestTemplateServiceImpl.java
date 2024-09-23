package com.danicode.marvel.service.impl;

import com.danicode.marvel.exception.ApiErrorException;
import com.danicode.marvel.service.HttpClientService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;

@Service
public class RestTemplateServiceImpl implements HttpClientService {

    private final RestTemplate restTemplate;

    public RestTemplateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T doGet(String endPoint, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = buildFinalUrl(endPoint, queryParams);

        // Request para peticiones POST o enviar las cabecaras o autorizaciones jwt, etc
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, responseType);

        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format(
                    "Error consumiento endpoint [ {} - {} ], código de respuesta es: {}",
                    HttpMethod.GET, endPoint, response.getStatusCode()
            );
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    @Override
    public <T, R> T doPost(String endPoint, Map<String, String> queryParams, Class<T> responseType, R requestBody) {
        String finalUrl = buildFinalUrl(endPoint, queryParams);

        // Request para peticiones POST o enviar las cabecaras o autorizaciones jwt, etc
        HttpEntity httpEntity = new HttpEntity(requestBody, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, responseType);

        if ((response.getStatusCode().value() != HttpStatus.OK.value()) ||
                (response.getStatusCode().value() != HttpStatus.CREATED.value())) {
            String message = String.format(
                    "Error consumiento endpoint [ {} - {} ], código de respuesta es: {}",
                    HttpMethod.POST, endPoint, response.getStatusCode()
            );
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    @Override
    public <T, R> T doPut(String endPoint, Map<String, String> queryParams, Class<T> responseType, R requestBody) {
        String finalUrl = buildFinalUrl(endPoint, queryParams);

        // Request para peticiones POST o enviar las cabecaras o autorizaciones jwt, etc
        HttpEntity httpEntity = new HttpEntity(requestBody, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, httpEntity, responseType);

        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format(
                    "Error consumiento endpoint [ {} - {} ], código de respuesta es: {}",
                    HttpMethod.PUT, endPoint, response.getStatusCode()
            );
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    @Override
    public <T> T doDelete(String endPoint, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = buildFinalUrl(endPoint, queryParams);

        // Request para peticiones POST o enviar las cabecaras o autorizaciones jwt, etc
        HttpEntity httpEntity = new HttpEntity(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.DELETE, httpEntity, responseType);

        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format(
                    "Error consumiento endpoint [ {} - {} ], código de respuesta es: {}",
                    HttpMethod.DELETE, endPoint, response.getStatusCode()
            );
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Tambien se puede enviar:
        // headers.put("Authorization", Collections.singletonList("bearer jwt"));

        return headers;
    }

    private static String buildFinalUrl(String endPoint, Map<String, String> queryParams) {
        // Clase para enviar la url y los query params
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endPoint);
        // Setear los query params
        if (queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        // Genera la url completa con los query params
        String finalUrl = builder.build().toUriString();
        return finalUrl;
    }
}
