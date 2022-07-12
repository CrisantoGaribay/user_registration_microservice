package com.unosquare.user_registration.controller;


import com.unosquare.user_registration.error.NotFoundException;
import com.unosquare.user_registration.error.StatusException;
import com.unosquare.user_registration.model.User;
import com.unosquare.user_registration.model.UserStatus;
import com.unosquare.user_registration.repositories.UserRepository;
import com.unosquare.user_registration.requestBody.newUserBody;
import com.unosquare.user_registration.requestBody.updateUserPassword;
import com.unosquare.user_registration.requestBody.updateUserStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/user/registration")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/saveUser")
    public User addUser(@Valid @RequestBody newUserBody newUser) {

        log.info("Creating user {} {}", newUser.getFirst_name(), newUser.getLast_name());

        User user = User
                .builder()
                .first_name(newUser.getFirst_name())
                .last_name(newUser.getLast_name())
                .address(newUser.getAddress())
                .phone_number(newUser.getPhone_number())
                .zip_code(newUser.getZip_code())
                .date_of_birth(newUser.getDate_of_birth())
                .country(newUser.getCountry())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .status(UserStatus.PENDING.toString())
                .build();

        return userRepository.insert(user);
    }

    @GetMapping("/getUserByEmail/{email}")
    public User getByEmail(@PathVariable String email) {

        log.info("Get user data of {}", email);
        return userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new NotFoundException(email));
    }

    @PatchMapping("/updatePasswordByEmail")
    public User updatePasswordByEmail(@Valid @RequestBody updateUserPassword updateUserPassword) {

        log.info("Updating user password for user {}", updateUserPassword.getEmail());

        User user = userRepository.findFirstByEmail(updateUserPassword.getEmail())
                .orElseThrow(() -> new NotFoundException(updateUserPassword.getEmail()));

        user.setPassword(updateUserPassword.getPassword());

        return userRepository.save(user);
    }

    @PatchMapping("/updateStatus")
    public User updateStatusByEmail(@Valid @RequestBody updateUserStatus updateUserStatus) {

        log.info("Updating user password for user {}", updateUserStatus.getEmail());

        UserStatus status = UserStatus.findByValue(updateUserStatus.getStatus().toUpperCase());
        if (status == null) {
            throw new StatusException();
        }

        User user = userRepository.findFirstByEmail(updateUserStatus.getEmail())
                .orElseThrow(() -> new NotFoundException(updateUserStatus.getEmail()));

        user.setStatus(status.toString());

        return userRepository.save(user);
    }

    @DeleteMapping("/deleteUserByEmail/{email}")
    public void deleteUserByEmail(@PathVariable String email) {

        log.info("Deleting user {}", email);

       userRepository.deleteByEmail(email);
    }
}
