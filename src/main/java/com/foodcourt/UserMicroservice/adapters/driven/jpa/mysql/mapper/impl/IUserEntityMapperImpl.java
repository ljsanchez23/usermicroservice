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
        if ( userEntity == null ) {
            return null;
        }
        Long id = null;
        String name = null;
        String lastName = null;
        Integer idDocument = 0;
        String phone = null;
        LocalDate dateOfBirth = null;
        String email = null;
        String password = null;
        Long roleId;



        id = userEntity.getId();
        name = userEntity.getName();
        lastName = userEntity.getLastName();
        idDocument = userEntity.getIdDocument();
        phone = userEntity.getPhone();
        dateOfBirth = userEntity.getDateOfBirth();
        email = userEntity.getEmail();
        password = userEntity.getPassword();
        roleId = userEntity.getRoleId().getId();


        return new User(id, name, lastName, idDocument, phone, dateOfBirth,email , password, roleId);
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
