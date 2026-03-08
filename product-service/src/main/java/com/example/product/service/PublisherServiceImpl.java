package com.example.product.service;

import com.example.product.entity.Publisher;
import com.example.product.model.CreatePublisherRequest;
import com.example.product.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

  private final PublisherRepository publisherRepository;

  @Override
  public Publisher createPublisher(CreatePublisherRequest publisherRequest) {

    Publisher publisher = new Publisher();
    publisher.setName(publisherRequest.getName());
    publisher.setAddress(publisherRequest.getAddress());
    publisher.setWebsite(publisherRequest.getWebsite());

    return publisherRepository.save(publisher);
  }
}
