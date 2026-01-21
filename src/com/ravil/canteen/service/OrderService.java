package com.ravil.canteen.service;

import com.ravil.canteen.exception.InvalidQuantityException;
import com.ravil.canteen.exception.MenuItemNotAvailableException;
import com.ravil.canteen.exception.OrderNotFoundException;
import com.ravil.canteen.model.MenuItem;
import com.ravil.canteen.model.Order;
import com.ravil.canteen.model.OrderItem;
import com.ravil.canteen.repository.OrderRepository;
import com.ravil.canteen.repository.OrderItemRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepo;          // работа с таблицей orders
    private final OrderItemRepository orderItemRepo;  // работа с таблицей order_items
    private final MenuService menuService;

    public OrderService(OrderRepository orderRepo, OrderItemRepository orderItemRepo, MenuService menuService) {
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.menuService = menuService;
    }

    public Order placeOrder(int customerId, List<int[]> items) {
        // создаём заказ
        Order order = orderRepo.createActive(customerId);

        for (int[] pair : items) {
            int menuItemId = pair[0];
            int qty = pair[1];
            if (qty <= 0) throw new InvalidQuantityException("Quantity must be > 0");

            MenuItem mi = menuService.getAvailableItem(menuItemId);
            if (mi == null) throw new MenuItemNotAvailableException("Menu item not available: " + menuItemId);

            BigDecimal unitPrice = mi.getPrice();
            orderItemRepo.addItem(order.getId(), menuItemId, qty, unitPrice); // добавляем позиции
        }
        return order;
    }

    public List<Order> viewActiveOrders() {
        return orderRepo.findActive();
    }

    public void markOrderCompleted(int orderId) {
        Optional<Order> o = orderRepo.findById(orderId);
        if (o.isEmpty()) throw new OrderNotFoundException("Order not found: " + orderId);
        orderRepo.markCompleted(orderId);
    }

    public List<OrderItem> getOrderItems(int orderId) {
        return orderItemRepo.findByOrderId(orderId);
    }
}
