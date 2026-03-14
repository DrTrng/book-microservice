package com.example.order.mapper;

import com.example.order.dto.OrderResponse;
import com.example.order.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseMapper {

  public OrderResponse toResponse(Order order) {
    return OrderResponse.builder()
        .id(order.getId())
        .bookId(order.getBookId())
        .userId(order.getUserId())
        .created(order.getCreated())
        .updated(order.getUpdated())
        .build();
  }
}
