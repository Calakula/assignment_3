package com.canteen.repository;

import com.canteen.entity.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemRepository {
    private final Connection connection;

    public OrderItemRepository(Connection connection) {
        this.connection = connection;
    }

    public void addOrderItem(OrderItem item) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, menu_item_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getMenuItemId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getSubtotal());
            stmt.executeUpdate();
        }
    }

    public List<OrderItem> findByOrderId(int orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                items.add(new OrderItem(rs.getInt("id"), rs.getInt("order_id"),
                        rs.getInt("menu_item_id"), rs.getInt("quantity"),
                        rs.getDouble("subtotal")));
            }
        }
        return items;
    }
}
