package com.unosquare.user_registration.repositories;

import com.unosquare.user_registration.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

//    @Query("{email: '?0'}")
    Optional<User> findFirstByEmail(String email);

    void deleteByEmail(String email);

}
