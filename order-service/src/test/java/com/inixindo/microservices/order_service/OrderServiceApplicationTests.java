package com.inixindo.microservices.order_service;

import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

  @ServiceConnection static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

  @LocalServerPort private Integer port;

  static {
    mySQLContainer.start();
  }

  @Test
  void shouldSubmitOrder() {
    String submitOrderJson =
        """
        {
          "orderNumber": "123",
          "skuCode": "SKU-123",
          "price": 100.0,
          "quantity": 2
        }
        """;
    var responseBodyString =
        RestAssured.given()
            .contentType("application/json")
            .body(submitOrderJson)
            .post("http://localhost:" + port + "/api/v1/orders")
            .then()
            .statusCode(201)
            .extract()
            .response()
            .asString();
    assertThat(responseBodyString, Matchers.is("Order placed successfully"));
  }
}
