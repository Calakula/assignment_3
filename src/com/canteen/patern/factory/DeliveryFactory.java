package com.canteen.pattern.factory;

public class DeliveryFactory {
    public static DeliveryOption createDelivery(String type) {
        return switch (type.toLowerCase()) {
            case "pickup" -> new PickupOrder();
            case "delivery" -> new DeliveryOrder();
            case "dinein" -> new DineInOrder();
            default -> throw new IllegalArgumentException("Unknown delivery type: " + type);
        };
    }
}
