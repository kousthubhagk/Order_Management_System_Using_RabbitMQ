package com.example.rabbitmq.service;

import com.example.rabbitmq.data.entities.OrderEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    public RabbitMQConsumer(OrderService orderService){
        this.objectMapper = new ObjectMapper();
        this.orderService = orderService;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consumeMessage(String message) {
        try {
            OrderEntity order = objectMapper.readValue(message, OrderEntity.class);
            System.out.println("Consumed order from RabbitMQ: " + order);

            // Example validation
            orderService.validateOrder(order);

        } catch (Exception e) {
            System.err.println("Failed to process message: " + e.getMessage());
        }
    }
}
