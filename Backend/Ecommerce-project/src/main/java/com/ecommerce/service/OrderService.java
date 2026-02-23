package com.ecommerce.service;

import com.ecommerce.dto.OrderItemRequest;
import com.ecommerce.dto.OrderItemResponse;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;

import jakarta.transaction.Transactional;

import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartitemRepository;
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Transactional
    public Order placeOrder(Long userId, List<OrderItemRequest> items, BigDecimal totalAmount) {

        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);

        order = orderRepository.save(order);

        System.out.println("Items received: " + items);

        if (items != null) {

            for (OrderItemRequest item : items) {

                System.out.println("Saving productId: " + item.getProductId());

                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(order.getId());
                orderItem.setProductId(item.getProductId());
                orderItem.setQuantity(item.getQuantity());

                orderItemRepository.save(orderItem);
            }

            Cart cart = cartRepository.findByUserId(userId);

            
            if (cart != null) {
                cartitemRepository.deleteByCartId(cart.getId());
                System.out.println("Cart cleared after order.");
            }
        }

        return order;
    }
    
    public List<OrderItemResponse> getOrderItems(Long orderId) {

        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        return items.stream().map(item -> {

            Product product = productRepository
                    .findById(item.getProductId())
                    .orElse(null);

            String productName = product != null ? product.getName() : "Unknown";

            return new OrderItemResponse(
                    item.getProductId(),
                    productName,
                    item.getQuantity()
            );

        }).toList();
    }    
}