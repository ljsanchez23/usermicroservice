package com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.impl;

import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.foodcourt.UserMicroservice.adapters.driven.jpa.mysql.mapper.IUserEntityMapper;
import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {
    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setName( user.getName() );
        userEntity.setLastName( user.getLastName() );
        userEntity.setIdDocument( user.getIdDocument() );
        userEntity.setPhone( user.getPhone() );
        userEntity.setDateOfBirth(user.getDateOfBirth());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        if (user.getRoleId() != null) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(user.getRoleId());
            userEntity.setRoleId(roleEntity);
        } else {
            userEntity.setRoleId(null);
        }

        return userEntity;
    }

    @Override
    public User toModel(UserEntity userEntity) {
            Long id = userEntity.getId();
            String name = userEntity.getName();
            String lastName = userEntity.getLastName();
            Integer idDocument = userEntity.getIdDocument();
            String phone = userEntity.getPhone();
            LocalDate dateOfBirth = userEntity.getDateOfBirth();
            String email = userEntity.getEmail();
            String password = userEntity.getPassword();
            Long roleId = userEntity.getRoleId().getId();

            return new User(id, name, lastName, idDocument, phone, dateOfBirth, email, password, roleId);
    }

    protected RoleEntity roleToRoleEntity(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( role.getId() );
        roleEntity.setName( role.getName() );

        return roleEntity;
    }

    protected Role roleEntityToRol(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        String name = null;
        Long id = null;

        name = roleEntity.getName();
        id = roleEntity.getId();

        return new Role( id, name);
    }
}
