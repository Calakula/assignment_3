package com.ravil.canteen;

import com.ravil.canteen.model.MenuItem;
import com.ravil.canteen.model.Order;
import com.ravil.canteen.model.OrderItem;
import com.ravil.canteen.repository.*;
import com.ravil.canteen.service.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Wire dependencies (manual DI)
        OrderRepository orderRepo = new OrderRepository();
        OrderItemRepository orderItemRepo = new OrderItemRepository();
        MenuService menuService = new MenuService(new MenuItemRepository());
        OrderService orderService = new OrderService(orderRepo, orderItemRepo, menuService);
        PaymentService paymentService = new PaymentService();

        // Show available menu
        System.out.println("Available menu:");
        for (MenuItem mi : menuService.listAvailable()) {
            System.out.printf("- %d: %s (%.2f)%n", mi.getId(), mi.getName(), mi.getPrice());
        }

        // Place an order for customer with id=1
        List<int[]> items = new ArrayList<>();
        items.add(new int[]{1, 2}); // Plov x2
        items.add(new int[]{3, 1}); // Tea x1

        Order order = orderService.placeOrder(1, items);
        System.out.println("Order created: #" + order.getId());

        // Calculate total
        List<OrderItem> orderItems = orderService.getOrderItems(order.getId());
        var total = paymentService.calculateTotal(orderItems);
        System.out.println("Order total: " + total);

        // View active orders
        System.out.println("Active orders:");
        for (Order o : orderService.viewActiveOrders()) {
            System.out.printf("Order #%d for customer %d, status=%s%n", o.getId(), o.getCustomerId(), o.getStatus());
        }

        // Mark as completed
        orderService.markOrderCompleted(order.getId());
        System.out.println("Order #" + order.getId() + " marked as COMPLETED.");
    }
}