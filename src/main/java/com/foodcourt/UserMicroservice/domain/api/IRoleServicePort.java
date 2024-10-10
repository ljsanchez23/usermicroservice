package com.foodcourt.UserMicroservice.domain.api;

import com.foodcourt.UserMicroservice.domain.model.Role;

public interface IRoleServicePort {
    void saveRole(Role role);
}
