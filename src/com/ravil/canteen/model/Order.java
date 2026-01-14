package com.ravil.canteen.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int customerId;
    private String status; // ACTIVE or COMPLETED
    private LocalDateTime createdAt;

    public Order() {}
    public Order(int id, int customerId, String status, LocalDateTime createdAt) {
        this.id = id; this.customerId = customerId; this.status = status; this.createdAt = createdAt;
    }
    public int getId() { return id; }
    public int getCustomerId() { return customerId; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setId(int id) { this.id = id; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setStatus(String status) { this.status = status; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
