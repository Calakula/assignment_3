package com.canteen.pattern.factory;

public class DeliveryOrder implements DeliveryOption {
    @Override
    public String getDetails() {
        return "Delivery order: food will be delivered to customer's address.";
    }
}
