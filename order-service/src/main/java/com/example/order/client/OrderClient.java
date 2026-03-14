package com.example.order.client;

import com.example.core.http.InternalHttpClient;
import com.example.order.dto.book.BookResponse;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderClient {

  private final ServicesProperties services;
  private final InternalHttpClient httpClient;

  public BookResponse getBookById(UUID bookId) {
    return httpClient.get(services.getProductUrl() + "/api/books/" + bookId, BookResponse.class);
  }
}
