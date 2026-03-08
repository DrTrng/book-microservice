package com.example.product.service;

import com.example.product.entity.Book;
import com.example.product.mapper.BookMapper;
import com.example.product.model.BookResponse;
import com.example.product.model.CreateBookRequest;
import com.example.product.repository.BookRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  public BookResponse createBook(CreateBookRequest request) {

    Book book = BookMapper.toEntity(request);

    book = bookRepository.save(book);

    return BookMapper.toResponse(book);
  }

  @Override
  public BookResponse getBook(UUID id) {

    Book book =
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

    return BookMapper.toResponse(book);
  }

  @Override
  public BookResponse updateBook(UUID id, CreateBookRequest request) {

    Book book =
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

    book.setTitle(request.getTitle());
    book.setDescription(request.getDescription());
    book.setIsbn(request.getIsbn());
    book.setPrice(request.getPrice());
    book.setPublishedDate(request.getPublishedDate());
    book.setLanguage(request.getLanguage());
    book.setPageCount(request.getPageCount());

    bookRepository.save(book);

    return BookMapper.toResponse(book);
  }

  @Override
  public void deleteBook(UUID id) {

    Book book =
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

    bookRepository.delete(book);
  }

  @Override
  public Page<BookResponse> getBooks(Pageable pageable) {

    return bookRepository.findAll(pageable).map(BookMapper::toResponse);
  }
}
