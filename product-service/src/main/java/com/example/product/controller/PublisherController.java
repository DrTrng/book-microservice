package com.example.product.controller;

import com.example.product.entity.Publisher;
import com.example.product.model.CreatePublisherRequest;
import com.example.product.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {

  private final PublisherService publisherService;

  @PostMapping
  public Publisher createPublisher(@RequestBody CreatePublisherRequest publisherRequest) {

    return publisherService.createPublisher(publisherRequest);
  }
}
