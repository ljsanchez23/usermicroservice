package com.foodcourt.UserMicroservice.adapters.driving.mapper.request.impl;

import com.foodcourt.UserMicroservice.adapters.driven.feign.client.util.EmployeeIdRequest;
import com.foodcourt.UserMicroservice.adapters.driving.dto.request.EmployeeRequest;
import com.foodcourt.UserMicroservice.adapters.driving.dto.request.UserRequest;
import com.foodcourt.UserMicroservice.adapters.driving.mapper.request.IEmployeeRequestMapper;
import com.foodcourt.UserMicroservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class IEmployeeRequestMapperImpl implements IEmployeeRequestMapper {

    @Override
    public User toModel(EmployeeRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        Long id = null;
        String name = employeeRequest.getName();
        String lastName = employeeRequest.getLastName();
        Integer idDocument = employeeRequest.getIdDocument();
        String phone = employeeRequest.getPhone();
        LocalDate dateOfBirth = null;
        String email = employeeRequest.getEmail();
        String password = employeeRequest.getPassword();
        Long roleId = null;


        return new User( id, name, lastName, idDocument, phone, dateOfBirth, email, password, roleId );
    }

    @Override
    public Long extractRestaurantIdFromRequest(EmployeeRequest employeeRequest) {
        return employeeRequest.getRestaurantId();
    }

    @Override
    public EmployeeIdRequest toRequest(Long id, Long restaurantId) {
        return new EmployeeIdRequest(id, restaurantId);
    }
}
