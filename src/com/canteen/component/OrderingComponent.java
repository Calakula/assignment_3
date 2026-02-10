// Ravil Maratov Add OrderingComponent
package com.canteen.component;

import com.canteen.entity.Order;
import com.canteen.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderingComponent {
    private final OrderService orderService;

    public OrderingComponent(OrderService orderService) {
        this.orderService = orderService;
    }

    public void createOrder(Order order) throws SQLException {
        orderService.placeOrder(order);
        System.out.println("Order created: " + order);
    }

    public void showActiveOrders() throws SQLException {
        List<Order> activeOrders = orderService.viewActiveOrders();
        activeOrders.forEach(System.out::println);
    }

    public void completeOrder(int orderId) throws SQLException {
        orderService.completeOrder(orderId);
        System.out.println("Order " + orderId + " marked as completed.");
    }
}
