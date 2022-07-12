package com.unosquare.user_registration.requestBody;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class newUserBody {

    @NotNull
    @NotBlank
    private String first_name;

    @NotNull
    @NotBlank
    private String last_name;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @NotBlank
    private String phone_number;

    @NotNull
    private Integer zip_code;

    @NotNull
    private LocalDate date_of_birth;

    @NotNull
    @NotBlank
    private String country;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;
}
