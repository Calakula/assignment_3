package com.canteen.service;

import com.canteen.entity.Order;
import com.canteen.entity.OrderItem;
import com.canteen.exception.OrderNotFoundException;
import com.canteen.repository.OrderRepository;
import com.canteen.repository.OrderItemRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void placeOrder(Order order) throws SQLException {
        orderRepository.addOrder(order);
        for (OrderItem item : order.getItems()) {
            orderItemRepository.addOrderItem(item);
        }
    }

    public List<Order> viewActiveOrders() throws SQLException {
        return orderRepository.findAll().stream()
                .filter(o -> "active".equalsIgnoreCase(o.getStatus()))
                .toList();
    }

    public void completeOrder(int orderId) throws SQLException {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException("Order with ID " + orderId + " not found.");
        }
        order.setStatus("completed");
        // В реальном проекте здесь нужно обновить запись в БД
    }
}
