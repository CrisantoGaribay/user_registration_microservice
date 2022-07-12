package com.unosquare.user_registration.error;

public class StatusException extends RuntimeException{

    public StatusException() {
        super("Status not valid");
    }

}
