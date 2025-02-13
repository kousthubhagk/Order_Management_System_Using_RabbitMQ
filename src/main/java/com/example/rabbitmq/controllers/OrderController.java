package com.example.rabbitmq.controllers;

import com.example.rabbitmq.data.entities.OrderEntity;
import com.example.rabbitmq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public String createOrder(@RequestBody OrderEntity order){
//        orderService.validateOrder(order);
        orderService.saveOrder(order);
        return "Order created successfully";
    }
}
