package com.inixindo.microservices.order_service.service;

import com.inixindo.microservices.order_service.dto.OrderRequest;
import com.inixindo.microservices.order_service.model.Order;
import com.inixindo.microservices.order_service.repository.OrderRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;

  public void placeOrder(OrderRequest orderRequest) {
    // Order order = new Order();
    // order.setOrderNumber(UUID.randomUUID().toString());
    // order.setSkuCode(orderRequest.skuCode());
    // order.setPrice(orderRequest.price());
    // order.setQuantity(orderRequest.quantity());
    // orderRepository.save(order);
    log.info(orderRequest.toString());
    Order order =
        Order.builder()
            .orderNumber(UUID.randomUUID().toString())
            .skuCode(orderRequest.skuCode())
            .price(orderRequest.price())
            .quantity(orderRequest.quantity())
            .build();
    orderRepository.save(order);
  }
}
