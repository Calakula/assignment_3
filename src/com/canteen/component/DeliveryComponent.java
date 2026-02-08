package com.canteen.component;

import com.canteen.pattern.factory.DeliveryFactory;
import com.canteen.pattern.factory.DeliveryOption;

public class DeliveryComponent {

    public void chooseDeliveryOption(String type) {
        DeliveryOption option = DeliveryFactory.createDelivery(type);
        System.out.println("Delivery option chosen: " + option.getDetails());
    }
}
