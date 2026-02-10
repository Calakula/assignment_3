package com.canteen.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int customerId;
    private String status; // active, completed
    private LocalDateTime createdAt;
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}

    public Order(int id, int customerId, String status, LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getter  & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Order{id=" + id + ", customerId=" + customerId + ", status='" + status + "', createdAt=" + createdAt + ", items=" + items + "}";
    }
}
