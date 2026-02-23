package com.ecommerce.controller;

import com.ecommerce.entity.OrderItem;
import com.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem item) {
        return orderItemService.saveOrderItem(item);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItem> getItems(@PathVariable Long orderId) {
        return orderItemService.getItemsByOrderId(orderId);
    }
}