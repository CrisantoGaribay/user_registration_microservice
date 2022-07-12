package com.unosquare.user_registration.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "USERS")
public class User {

    @Id
    private String id;

    private String first_name;

    private String last_name;

    private String address;

    private String phone_number;

    private Integer zip_code;

    private LocalDate date_of_birth;

    private String country;

    private String email;

    private String password;

    private String status;


}
