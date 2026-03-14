package com.example.order.service.impl;

import com.example.core.exception.BookBaseException;
import com.example.order.client.OrderClient;
import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.dto.book.BookResponse;
import com.example.order.entity.Order;
import com.example.order.exception.BookException;
import com.example.order.mapper.OrderResponseMapper;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final OrderResponseMapper orderResponseMapper;

  private final OrderClient orderClient;

  @Override
  @Transactional
  public OrderResponse create(OrderRequest request) {
    // Check exist book and user in real application
    BookResponse bookResponse = orderClient.getBookById(request.getBookId());
    if (bookResponse == null) {
      throw new BookBaseException(BookException.BOOK_NOT_EXIST, request.getBookId());
    }
    Order order =
        Order.builder()
            .id(UUID.randomUUID())
            .bookId(request.getBookId())
            .userId(request.getUserId())
            .build();
    order = orderRepository.save(order);
    return orderResponseMapper.toResponse(order);
  }

  @Override
  public OrderResponse getById(UUID id) {
    Order order =
        orderRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Order not found: " + id));
    return orderResponseMapper.toResponse(order);
  }

  @Override
  public List<OrderResponse> getAll() {
    return orderRepository.findAll().stream().map(orderResponseMapper::toResponse).toList();
  }

  @Override
  @Transactional
  public OrderResponse update(UUID id, OrderRequest request) {
    Order order =
        orderRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Order not found: " + id));
    order.setBookId(request.getBookId());
    order.setUserId(request.getUserId());
    order = orderRepository.save(order);
    return orderResponseMapper.toResponse(order);
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    if (!orderRepository.existsById(id)) {
      throw new org.springframework.web.server.ResponseStatusException(
          org.springframework.http.HttpStatus.NOT_FOUND, "Order not found: " + id);
    }
    orderRepository.deleteById(id);
  }
}
