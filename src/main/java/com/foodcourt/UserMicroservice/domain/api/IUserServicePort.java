package com.foodcourt.UserMicroservice.domain.api;

import com.foodcourt.UserMicroservice.domain.model.User;

public interface IUserServicePort {
    void saveUser(String roleName, User user);
    User findUserById(Long id);
    void saveCustomer(User user);
    User saveEmployee(User user);
    void addEmployeeToRestaurant(Long restaurantId, Long employeeId);
}
