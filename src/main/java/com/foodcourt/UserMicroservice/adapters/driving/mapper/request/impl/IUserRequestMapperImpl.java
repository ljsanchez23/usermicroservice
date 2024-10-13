package com.foodcourt.UserMicroservice.adapters.driving.mapper.request.impl;

import com.foodcourt.UserMicroservice.adapters.driving.mapper.request.IUserRequestMapper;
import com.foodcourt.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.foodcourt.UserMicroservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class IUserRequestMapperImpl implements IUserRequestMapper {

    @Override
    public User toModel(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        Long id = null;
        String name = userRequest.getName();
        String lastName = userRequest.getLastName();
        Integer idDocument = userRequest.getIdDocument();
        String phone = userRequest.getPhone();
        LocalDate dateOfBirth = userRequest.getDateOfBirth();
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();
        Long roleId = null;


        return new User( id, name, lastName, idDocument, phone, dateOfBirth, email, password, roleId );
    }
}
