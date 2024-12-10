package com.example.order.controller;


import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {


    private  final OrderService orderService;

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestParam int stock) {
        return orderService.updateOrder(id, stock);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllProducts();
    }
}
