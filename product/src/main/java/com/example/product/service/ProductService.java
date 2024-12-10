package com.example.product.service;


import com.example.product.entity.Product;
import com.example.product.publisher.KafkaPublisher;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final KafkaPublisher kafkaPublisher;
    private final ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateStock(Long id, int stock) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setStock(stock);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProcessByIdFromOutbox(Long id) {

        kafkaPublisher.publish("delete-process-byId-from-outbox", id);
    }
}
