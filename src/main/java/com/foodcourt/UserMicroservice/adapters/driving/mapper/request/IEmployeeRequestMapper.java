package com.foodcourt.UserMicroservice.adapters.driving.mapper.request;

import com.foodcourt.UserMicroservice.adapters.driven.feign.client.util.EmployeeIdRequest;
import com.foodcourt.UserMicroservice.adapters.driving.dto.request.EmployeeRequest;
import com.foodcourt.UserMicroservice.domain.model.User;

public interface IEmployeeRequestMapper {
   User toModel(EmployeeRequest employeeRequest);
   Long extractRestaurantIdFromRequest(EmployeeRequest employeeRequest);
   EmployeeIdRequest toRequest(Long id, Long restaurantId);
}
