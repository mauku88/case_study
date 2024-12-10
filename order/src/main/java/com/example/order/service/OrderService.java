package com.example.order.service;


import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;

    public Order addOrder(Order product) {
        return orderRepository.save(product);
    }

    public Order updateOrder(Long id, int stock) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStock(stock);
        return orderRepository.save(order);
    }

    public List<Order> getAllProducts() {
        return orderRepository.findAll();
    }
}
