package com.example.order.controller;

import com.example.core.dto.ApiResponse;
import com.example.order.dto.OrderRequest;
import com.example.order.dto.OrderResponse;
import com.example.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "Order CRUD API")
public class OrderController {

  private final OrderService orderService;

  @Operation(summary = "Create order")
  @PostMapping
  public ResponseEntity<ApiResponse<OrderResponse>> create(@RequestBody OrderRequest request) {
    OrderResponse created = orderService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ApiResponse.of(HttpStatus.CREATED, created));
  }

  @Operation(summary = "Get order by ID")
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderResponse>> getById(@PathVariable UUID id) {
    OrderResponse order = orderService.getById(id);
    return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK, order));
  }

  @Operation(summary = "Get all orders")
  @GetMapping
  public ResponseEntity<ApiResponse<List<OrderResponse>>> getAll() {
    List<OrderResponse> orders = orderService.getAll();
    return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK, orders));
  }

  @Operation(summary = "Update order")
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<OrderResponse>> update(
      @PathVariable UUID id, @RequestBody OrderRequest request) {
    OrderResponse updated = orderService.update(id, request);
    return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK, updated));
  }

  @Operation(summary = "Delete order")
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
    orderService.delete(id);
    return ResponseEntity.ok(ApiResponse.<Void>of(HttpStatus.OK, null));
  }
}
