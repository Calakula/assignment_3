package com.ravil.canteen.repository;

import com.ravil.canteen.model.OrderItem;
import com.ravil.canteen.util.Db;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {

    // Добавить позицию в заказ
    public OrderItem addItem(int orderId, int menuItemId, int quantity, BigDecimal unitPrice) {
        String sql = "INSERT INTO order_items (order_id, menu_item_id, quantity, unit_price) " +
                "VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, menuItemId);
            ps.setInt(3, quantity);
            ps.setBigDecimal(4, unitPrice);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new OrderItem(rs.getInt(1), orderId, menuItemId, quantity, unitPrice);
                }
            }
            throw new RuntimeException("Не удалось добавить элемент заказа");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<OrderItem> findByOrderId(int orderId) {
        String sql = "SELECT id, order_id, menu_item_id, quantity, unit_price FROM order_items WHERE order_id = ?";
        List<OrderItem> list = new ArrayList<>();
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new OrderItem(
                            rs.getInt("id"),
                            rs.getInt("order_id"),
                            rs.getInt("menu_item_id"),
                            rs.getInt("quantity"),
                            rs.getBigDecimal("unit_price")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
