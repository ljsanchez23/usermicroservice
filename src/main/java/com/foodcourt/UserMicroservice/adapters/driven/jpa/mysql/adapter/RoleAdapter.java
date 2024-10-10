package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.adapter;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.repository.IRoleRepository;
import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.spi.IRolePersistencePort;

public class RoleAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    public RoleAdapter(IRoleRepository roleRepository, IRoleEntityMapper roleEntityMapper){
        this.roleRepository = roleRepository;
        this.roleEntityMapper =roleEntityMapper;
    }

    @Override
    public void saveRole(Role role){
        roleRepository.save(roleEntityMapper.toEntity(role));
    }
}
