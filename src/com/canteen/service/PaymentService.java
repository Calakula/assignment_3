
package com.canteen.service;

import com.canteen.entity.Order;
import com.canteen.entity.OrderItem;

public class PaymentService {

    public double calculateTotal(Order order) {
        return order.getItems().stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();
    }

    public boolean processPayment(Order order, double amountPaid) {
        double total = calculateTotal(order);
        return amountPaid >= total;
    }
}
