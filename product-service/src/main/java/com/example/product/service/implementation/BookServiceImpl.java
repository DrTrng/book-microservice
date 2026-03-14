package com.example.product.service.implementation;

import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.product.entity.Book;
import com.example.product.mapper.BookMapper;
import com.example.product.model.BookResponse;
import com.example.product.model.CreateBookRequest;
import com.example.product.repository.BookRepository;
import com.example.product.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Override
  // @CacheEvict(value = "book_list", allEntries = true)
  public BookResponse createBook(CreateBookRequest request) {

    Book book = BookMapper.toEntity(request);
    book = bookRepository.save(book);

    return BookMapper.toResponse(book);
  }

  @Override
  // @Cacheable(value = "books", key = "#id")
  public BookResponse getBook(UUID id) {

    Book book =
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

    return BookMapper.toResponse(book);
  }

  @Override
  // @Caching(
  //     put = {@CachePut(value = "books", key = "#id")},
  //     evict = {@CacheEvict(value = "book_list", allEntries = true)})
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

    book = bookRepository.save(book);

    return BookMapper.toResponse(book);
  }

  @Override
  // @Caching(
  //     evict = {
  //       @CacheEvict(value = "books", key = "#id"),
  //       @CacheEvict(value = "book_list", allEntries = true)
  //     })
  public void deleteBook(UUID id) {

    Book book =
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

    bookRepository.delete(book);
  }

  @Override
  // @Cacheable(value = "book_list", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
  public Page<BookResponse> getBooks(Pageable pageable) {

    return bookRepository.findAll(pageable).map(BookMapper::toResponse);
  }
}
