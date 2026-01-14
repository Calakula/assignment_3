package com.ravil.canteen.repository;

import com.ravil.canteen.model.Customer;
import com.ravil.canteen.util.Db;

import java.sql.*;
import java.util.Optional;

public class CustomerRepository {
    public Optional<Customer> findByEmail(String email) {
        String sql = "SELECT id, name, email FROM customers WHERE email = ?";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Customer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Customer save(Customer customer) {
        String sql = "INSERT INTO customers (name, email) VALUES (?, ?) RETURNING id";
        try (Connection c = Db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) customer.setId(rs.getInt(1));
            }
            return customer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
