package com.danicode.marvel.service;

// RESTTemplate => es la clase que ofrece Spring para el acceso desde la parte cliente a Servicios REST.
// Devolvera genericos

import java.util.Map;

public interface HttpClientService {

    <T> T doGet(String endPoint, Map<String, String> queryParams, Class<T> responseType);
    <T, R> T doPost(String endPoint, Map<String, String> queryParams, Class<T> responseType, R requestBody);
    <T, R> T doPut(String endPoint, Map<String, String> queryParams, Class<T> responseType, R requestBody);
    <T> T doDelete(String endPoint, Map<String, String> queryParams, Class<T> responseType);
}
