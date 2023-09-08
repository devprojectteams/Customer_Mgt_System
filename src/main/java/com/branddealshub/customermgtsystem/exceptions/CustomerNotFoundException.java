package com.branddealshub.customermgtsystem.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
