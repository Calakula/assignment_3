package com.canteen.exception;

public class MenuItemNotAvailableException extends RuntimeException {
    public MenuItemNotAvailableException(String message) {
        super(message);
    }
}

