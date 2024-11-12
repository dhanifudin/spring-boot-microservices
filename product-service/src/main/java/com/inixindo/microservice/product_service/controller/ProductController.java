package com.inixindo.microservice.product_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inixindo.microservice.product_service.service.ProductService;
import com.inixindo.microservice.product_service.dto.ProductResponse;
import com.inixindo.microservice.product_service.dto.ProductRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  
  private final ProductService productService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
    return productService.createProduct(productRequest);
  }
  
  @GetMapping
  public List<ProductResponse> getAllProducts() {
    return productService.getProducts();
  }
}
