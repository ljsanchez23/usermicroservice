package com.foodcourt.UserMicroservice.adapters.driven.feign.client;
import com.foodcourt.UserMicroservice.adapters.driven.feign.client.util.EmployeeIdRequest;
import com.foodcourt.UserMicroservice.adapters.util.AdaptersConstants;

import com.foodcourt.UserMicroservice.configuration.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = AdaptersConstants.FOOD_COURT_CLIENT_NAME,
        url = AdaptersConstants.FOOD_COURT_CLIENT_URL,
        configuration = FeignConfiguration.class)
public interface IFoodCourtFeignClient {
    @PostMapping(value = AdaptersConstants.FOOD_COURT_ADD_EMPLOYEE_ENDPOINT)
    void addEmployeeToRestaurant(@RequestBody EmployeeIdRequest employeeIdRequest);
}
