package com.inixindo.microservices.inventory_service.repository;

import com.inixindo.microservices.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, int quantity);
}