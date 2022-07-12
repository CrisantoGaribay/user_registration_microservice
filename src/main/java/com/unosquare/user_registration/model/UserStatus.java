package com.unosquare.user_registration.model;

import lombok.Getter;

import java.util.stream.Stream;

public enum UserStatus {

    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    EXPIRED("EXPIRED");

    @Getter
    private final String value;

    UserStatus(String value) {
        this.value = value;
    }

    public static UserStatus findByValue(String value) {
        UserStatus result = null;
        return Stream.of(UserStatus.values())
                .filter(caller -> caller.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }
}
