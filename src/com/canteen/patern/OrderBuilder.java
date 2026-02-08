package com.canteen.pattern.builder;

import com.canteen.entity.Order;
import com.canteen.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    private int id;
    private int customerId;
    private String status;
    private LocalDateTime createdAt;
    private List<OrderItem> items = new ArrayList<>();

    public OrderBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setCustomerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }
 
    public OrderBuilder setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public OrderBuilder addItem(OrderItem item) {
        this.items.add(item);
        return this;
    }

    public Order build() {
        Order order = new Order(id, customerId, status, createdAt);
        order.setItems(items);
        return order;
    }
}
