package com.ecommerce.controller;

import com.ecommerce.dto.OrderItemResponse;
import com.ecommerce.dto.PlaceOrderRequest;
import com.ecommerce.entity.Order;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.service.OrderService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
    @PostMapping("/place")
    public Order placeOrder(@RequestBody PlaceOrderRequest request) {

        System.out.println("USER ID: " + request.getUserId());
        System.out.println("TOTAL: " + request.getTotalAmount());
        System.out.println("ITEMS: " + request.getItems());  // ADD THIS

        return orderService.placeOrder(
            request.getUserId(),
            request.getItems(),
            request.getTotalAmount()
        );
    }    
    @GetMapping("/my-orders/{userId}")
    public List<Order> getMyOrders(@PathVariable Long userId) {
        return orderRepository.findByUserId(userId);
    }
    
    @GetMapping("/{orderId}/items")
    public List<OrderItemResponse> getOrderItems(@PathVariable Long orderId) {
        return orderService.getOrderItems(orderId);
    }
    
}