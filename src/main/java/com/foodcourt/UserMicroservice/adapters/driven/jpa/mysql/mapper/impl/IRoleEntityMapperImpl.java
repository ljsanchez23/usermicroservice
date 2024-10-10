package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IRoleEntityMapper;
import com.foodcourt.UserMicroservice.domain.model.Role;
import org.springframework.stereotype.Component;

@Component
public class IRoleEntityMapperImpl implements IRoleEntityMapper {
    @Override
    public RoleEntity toEntity(Role role){
        if ( role == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( role.getId() );
        roleEntity.setName( role.getName() );

        return roleEntity;
    }

    @Override
    public Role toModel(RoleEntity roleEntity){
        if ( roleEntity == null ) {
            return null;
        }

        String name = null;
        Long id = null;

        name = roleEntity.getName();
        id = roleEntity.getId();

        return new Role( id, name );
    }
}
