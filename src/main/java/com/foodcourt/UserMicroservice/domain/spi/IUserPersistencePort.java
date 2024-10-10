package com.foodcourt.UserMicroservice.domain.spi;

import com.foodcourt.UserMicroservice.domain.model.User;

public interface IUserPersistencePort {
    void saveUser(User user);
    boolean existsByEmail(String email);
}
