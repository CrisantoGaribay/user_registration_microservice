package com.unosquare.user_registration.error;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String resource) {
        super(
                String.format("%s not found", resource)
        );
    }

}
