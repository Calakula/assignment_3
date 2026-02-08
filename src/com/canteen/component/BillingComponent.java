package com.canteen.component;

import com.canteen.entity.Order;
import com.canteen.pattern.singleton.TaxConfig;
import com.canteen.service.PaymentService;

public class BillingComponent {
    private final PaymentService paymentService;

    public BillingComponent(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void calculateBill(Order order) {
        double subtotal = paymentService.calculateTotal(order);
        double tax = subtotal * TaxConfig.getInstance().getTaxRate();
        double total = subtotal + tax;

        System.out.println("Subtotal: " + subtotal);
        System.out.println("Tax (" + TaxConfig.getInstance().getTaxRate() * 100 + "%): " + tax);
        System.out.println("Total: " + total);
    }

    public void processPayment(Order order, double amountPaid) {
        boolean success = paymentService.processPayment(order, amountPaid);
        if (success) {
            System.out.println("Payment successful. Amount paid: " + amountPaid);
        } else {
            System.out.println("Payment failed. Amount paid: " + amountPaid);
        }
    }
}
