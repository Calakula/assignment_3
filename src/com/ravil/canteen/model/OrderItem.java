package com.ravil.canteen.model;

import java.math.BigDecimal;

public class OrderItem {
    private int id;
    private int orderId;
    private int menuItemId;
    private int quantity; 
    private BigDecimal unitPrice;

    public OrderItem() {}
    public OrderItem(int id, int orderId, int menuItemId, int quantity, BigDecimal unitPrice) {
        this.id = id; this.orderId = orderId; this.menuItemId = menuItemId;
        this.quantity = quantity; this.unitPrice = unitPrice;
    }
    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public int getMenuItemId() { return menuItemId; }
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setId(int id) { this.id = id; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setMenuItemId(int menuItemId) { this.menuItemId = menuItemId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
}
