package com.inixindo.microservice.product_service.repository;

import com.inixindo.microservice.product_service.model.Product;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
  List<Product> findByNameContaining(String name);
}
