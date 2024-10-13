package com.foodcourt.UserMicroservice.domain.api.usecase;

import com.foodcourt.UserMicroservice.domain.api.IRoleServicePort;
import com.foodcourt.UserMicroservice.domain.exception.RoleNotFoundException;
import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.spi.IRolePersistencePort;
import com.foodcourt.UserMicroservice.domain.util.Constants;


public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort){
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveRole(Role role){
        rolePersistencePort.saveRole(role);
    }

    @Override
    public String getRoleNameById(Long id) {
        String roleName = rolePersistencePort.getRoleNameById(id);
        if (roleName == null) {
            throw new RoleNotFoundException(Constants.ROLE_NOT_FOUND);
        }
        return roleName;
    }
}
