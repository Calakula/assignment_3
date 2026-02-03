package com.ravil.canteen.service;

import com.ravil.canteen.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
 
public class PaymentService {
    public BigDecimal calculateTotal(List&lt;OrderItem&gt; items) {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem oi : items) {
            total = total.add(oi.getUnitPrice().multiply(BigDecimal.valueOf(oi.getQuantity())));
        }
        return total;
    }
