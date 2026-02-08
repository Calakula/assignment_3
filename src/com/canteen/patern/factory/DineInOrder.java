package com.canteen.pattern.factory;

public class DineInOrder implements DeliveryOption {
    @Override
    public String getDetails() {
        return "Dine-in order: customer will eat inside the canteen.";
    }
}
