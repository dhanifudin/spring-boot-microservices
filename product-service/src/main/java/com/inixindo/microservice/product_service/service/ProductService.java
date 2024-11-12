package com.inixindo.microservice.product_service.service;

import com.inixindo.microservice.product_service.dto.*;
import com.inixindo.microservice.product_service.model.Product;
import com.inixindo.microservice.product_service.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;

  public ProductResponse createProduct(ProductRequest productRequest) {
    Product product =
        Product.builder()
            .name(productRequest.name())
            .description(productRequest.description())
            .price(productRequest.price())
            .build();
    productRepository.save(product);
    log.info("Product created");
    return new ProductResponse(
        product.getId(), product.getName(), product.getDescription(), product.getPrice());
  }

  public List<ProductResponse> getProducts() {
    return productRepository.findAll().stream()
        .map(
            product ->
                new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()))
        .toList();
  }
}
