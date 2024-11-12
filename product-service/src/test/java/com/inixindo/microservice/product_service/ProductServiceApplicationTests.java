package com.inixindo.microservice.product_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

  @ServiceConnection
  static MongoDBContainer mongoDBContainer =
      new MongoDBContainer(DockerImageName.parse("mongo:7.0.5"));

  @LocalServerPort Integer port;

  @BeforeEach
  void setup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
  }

  static {
    mongoDBContainer.start();
    System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
  }

  @Test
  void shouldCreateProduct() {
    String requestBody =
        """
          {
        	  "name": "oppo",
        	  "description": "Oppo android phone",
        	  "price": 2000000
          }
        """;

    RestAssured.given()
        .contentType("application/json")
        .body(requestBody)
        .when()
        .post("/api/v1/products")
        .then()
        .statusCode(201)
        .body("id", Matchers.notNullValue());
  }
}
