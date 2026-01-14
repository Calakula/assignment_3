package com.ravil.canteen.model;

import java.math.BigDecimal;

public class MenuItem {
    private int id;
    private String name;
    private BigDecimal price;
    private boolean available;

    public MenuItem() {}
    public MenuItem(int id, String name, BigDecimal price, boolean available) {
        this.id = id; this.name = name; this.price = price; this.available = available;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setAvailable(boolean available) { this.available = available; }
}
