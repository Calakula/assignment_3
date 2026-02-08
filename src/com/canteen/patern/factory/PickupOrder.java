package com.canteen.pattern.factory;

public class PickupOrder implements DeliveryOption {
    @Override
    public String getDetails() {
        return "Pickup order: customer will collect food at the counter.";
    }
}
