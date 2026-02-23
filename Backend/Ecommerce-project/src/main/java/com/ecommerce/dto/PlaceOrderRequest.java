package com.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public class PlaceOrderRequest {

    private Long userId;
    private List<OrderItemRequest> items;
    private BigDecimal totalAmount;

    public PlaceOrderRequest() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}