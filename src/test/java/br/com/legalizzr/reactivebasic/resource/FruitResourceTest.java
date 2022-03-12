package br.com.legalizzr.reactivebasic.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@QuarkusTest
class FruitResourceTest {

    Response response = RestAssured.given()
            .when()
            .get("/v1/fruits")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .extract()
            .response();

}