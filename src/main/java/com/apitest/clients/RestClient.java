package com.apitest.clients;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RestClient {
    private final RequestSpecification requestSpec;

    public RestClient(String baseUri) {
        this.requestSpec = new RequestSpecBuilder()
            .setBaseUri(baseUri)
            .setContentType(ContentType.JSON)
            .build();
    }

    public Response get(String endpoint) {
        log.info("Making GET request to: {}", endpoint);
        return RestAssured.given()
            .spec(requestSpec)
            .get(endpoint);
    }

    public Response post(String endpoint, Object body) {
        log.info("Making POST request to: {}", endpoint);
        return RestAssured.given()
            .spec(requestSpec)
            .body(body)
            .post(endpoint);
    }

    public Response put(String endpoint, Object body) {
        log.info("Making PUT request to: {}", endpoint);
        return RestAssured.given()
            .spec(requestSpec)
            .body(body)
            .put(endpoint);
    }

    public Response delete(String endpoint) {
        log.info("Making DELETE request to: {}", endpoint);
        return RestAssured.given()
            .spec(requestSpec)
            .delete(endpoint);
    }
} 