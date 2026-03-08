package com.example.product.repository;

import com.example.product.entity.Publisher;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, UUID> {}
