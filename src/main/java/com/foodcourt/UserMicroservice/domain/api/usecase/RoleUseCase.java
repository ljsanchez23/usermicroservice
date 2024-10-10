package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.api.IRoleServicePort;
import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort){
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveRole(Role role){
        rolePersistencePort.saveRole(role);
    }
}
