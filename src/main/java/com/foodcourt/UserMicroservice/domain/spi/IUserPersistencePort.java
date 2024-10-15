package com.foodcourt.UserMicroservice.domain.spi;

import com.foodcourt.UserMicroservice.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveUser(User user);
    boolean existsByEmail(String email);
    Optional<User> findUserById(Long id);
    User findByEmail(String email);
    User saveEmployee(User user);
}
