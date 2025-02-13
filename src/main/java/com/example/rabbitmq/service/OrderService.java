package com.example.rabbitmq.service;

import com.example.rabbitmq.data.entities.OrderEntity;
import com.example.rabbitmq.data.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitMqProducer rabbitMqProducer;
    public boolean validateOrder(OrderEntity order){
        if(order.getTotalAmount() <= 0){
            throw new IllegalArgumentException("Total amount should be greater than 0");
        }
        return true;
    }

    public OrderEntity saveOrder(OrderEntity order){
        validateOrder(order);
        System.out.println("Order saved: " + order);
        OrderEntity savedOrder = orderRepository.save(order);
        rabbitMqProducer.sendOrder(savedOrder);
        return savedOrder;
//        return null;
    }
}
