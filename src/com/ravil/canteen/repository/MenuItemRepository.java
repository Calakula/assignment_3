package com.ravil.canteen.repository;

import com.ravil.canteen.model.MenuItem;
import com.ravil.canteen.util.Db;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;


public class MenuItemRepository {
    public Optional<MenuItem> findById(int id) {
        String sql = "SELECT id, name, price, available FROM menu_items WHERE id = ?";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<MenuItem> findAllAvailable() {
        String sql = "SELECT id, name, price, available FROM menu_items WHERE available = TRUE";
        List<MenuItem> list = new ArrayList<>();
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private MenuItem map(ResultSet rs) throws SQLException {
        return new MenuItem(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBigDecimal("price"),
                rs.getBoolean("available")
        );
    }
}
