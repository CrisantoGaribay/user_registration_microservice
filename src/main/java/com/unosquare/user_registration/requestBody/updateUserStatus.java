package com.unosquare.user_registration.requestBody;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class updateUserStatus {

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String status;
}
