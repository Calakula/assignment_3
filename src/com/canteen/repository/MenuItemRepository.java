package com.canteen.repository;

import com.canteen.entity.MenuItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository {
    private final Connection connection;

    public MenuItemRepository(Connection connection) {
        this.connection = connection;
    }

    public void addMenuItem(MenuItem item) throws SQLException {
        String sql = "INSERT INTO menu_items (name, description, price, available) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getDescription());
            stmt.setDouble(3, item.getPrice());
            stmt.setBoolean(4, item.isAvailable());
            stmt.executeUpdate();
        }
    }

    public MenuItem findById(int id) throws SQLException {
        String sql = "SELECT * FROM menu_items WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new MenuItem(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getDouble("price"),
                        rs.getBoolean("available"));
            }
        }
        return null;
    }

    public List<MenuItem> findAll() throws SQLException {
        List<MenuItem> items = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                items.add(new MenuItem(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"), rs.getDouble("price"),
                        rs.getBoolean("available")));
            }
        }
        return items;
    }
}
