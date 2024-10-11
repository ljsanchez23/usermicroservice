package com.foodcourt.UserMicroservice.domain.api;

import com.foodcourt.UserMicroservice.domain.model.User;

public interface IUserServicePort {
    void saveUser(User user);
    User findUserById(Long id);
}
