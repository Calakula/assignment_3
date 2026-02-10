package com.canteen.entity;

public class OrderItem {
    private int id;
    private int orderId;
    private int menuItemId;
    private int quantity;
    private double subtotal;

    public OrderItem() {}

    public OrderItem(int id, int orderId, int menuItemId, int quantity, double subtotal) {
        this.id = id;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    // Get & Set
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getMenuItemId() { return menuItemId; }
    public void setMenuItemId(int menuItemId) { this.menuItemId = menuItemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    @Override
    public String toString() {
        return "OrderItem{id=" + id + ", orderId=" + orderId + ", menuItemId=" + menuItemId + ", quantity=" + quantity + ", subtotal=" + subtotal + "}";
    }
}
