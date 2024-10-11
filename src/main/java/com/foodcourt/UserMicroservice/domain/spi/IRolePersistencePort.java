package com.foodcourt.UserMicroservice.domain.spi;

import com.foodcourt.UserMicroservice.domain.model.Role;

public interface IRolePersistencePort {
    void saveRole(Role role);
    String getRoleNameById(Long id);
}
