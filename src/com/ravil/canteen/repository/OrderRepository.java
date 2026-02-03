package com.ravil.canteen.repository;

import com.ravil.canteen.model.Order;
import com.ravil.canteen.util.Db;

import java.sql.*;
import java.util.*;


public class OrderRepository {
    public Order createActive(int customerId) {
        String sql = "INSERT INTO orders (customer_id, status) VALUES (?, 'ACTIVE') RETURNING id, created_at";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Order(rs.getInt("id"), customerId, "ACTIVE",
                            rs.getTimestamp("created_at").toLocalDateTime());
                }
            }
            throw new RuntimeException("Failed to create order");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Order> findById(int id) {
        String sql = "SELECT id, customer_id, status, created_at FROM orders WHERE id = ?";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Order(
                            rs.getInt("id"),
                            rs.getInt("customer_id"),
                            rs.getString("status"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<Order> findActive() {
        String sql = "SELECT id, customer_id, status, created_at FROM orders WHERE status = 'ACTIVE' ORDER BY created_at DESC";
        List<Order> list = new ArrayList<>();
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void markCompleted(int orderId) {
        String sql = "UPDATE orders SET status = 'COMPLETED' WHERE id = ?";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            if (ps.executeUpdate() == 0) {
                throw new RuntimeException("Order not updated");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
