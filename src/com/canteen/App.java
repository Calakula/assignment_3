
package com.canteen;

import com.canteen.entity.MenuItem;
import com.canteen.entity.Order;
import com.canteen.entity.OrderItem;
import com.canteen.repository.MenuItemRepository;
import com.canteen.repository.OrderItemRepository;
import com.canteen.service.MenuService;
import com.canteen.service.OrderService;
import com.canteen.service.PaymentService;
import com.ravil.canteen.repository.*;
import com.ravil.canteen.service.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        OrderRepository orderRepo = new OrderRepository();
        OrderItemRepository orderItemRepo = new OrderItemRepository();
        MenuService menuService = new MenuService(new MenuItemRepository());
        OrderService orderService = new OrderService(orderRepo, orderItemRepo, menuService);
        PaymentService paymentService = new PaymentService();

        System.out.println("Available menu:");
        for (MenuItem mi : menuService.listAvailable()) {
            System.out.printf("- %d: %s (%.2f)%n", mi.getId(), mi.getName(), mi.getPrice());
        }
        List<int[]> items = new ArrayList<>();
        items.add(new int[]{1, 2});
        items.add(new int[]{3, 1});

        Order order = orderService.placeOrder(1, items);
        System.out.println("Order created: #" + order.getId());

        List<OrderItem> orderItems = orderService.getOrderItems(order.getId());
        var total = paymentService.calculateTotal(orderItems);
        System.out.println("Order total: " + total);

        System.out.println("Active orders:");
        for (Order o : orderService.viewActiveOrders()) {
            System.out.printf("Order #%d for customer %d, status=%s%n", o.getId(), o.getCustomerId(), o.getStatus());
        }

        orderService.markOrderCompleted(order.getId());
        System.out.println("Order #" + order.getId() + " marked as COMPLETED.");
    }
}
