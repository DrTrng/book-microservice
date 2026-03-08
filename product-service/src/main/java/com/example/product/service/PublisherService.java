package com.example.product.service;

import com.example.product.entity.Publisher;
import com.example.product.model.CreatePublisherRequest;

public interface PublisherService {

  Publisher createPublisher(CreatePublisherRequest publisher);
}
