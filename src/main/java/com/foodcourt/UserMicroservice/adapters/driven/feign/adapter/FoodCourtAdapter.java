package com.foodcourt.UserMicroservice.adapters.driven.feign.adapter;


import com.foodcourt.UserMicroservice.adapters.driven.feign.client.IFoodCourtFeignClient;
import com.foodcourt.UserMicroservice.adapters.driving.mapper.request.IEmployeeRequestMapper;
import com.foodcourt.UserMicroservice.domain.spi.IFoodCourtPort;

public class FoodCourtAdapter implements IFoodCourtPort {

    private final IFoodCourtFeignClient foodCourtFeignClient;
    private final IEmployeeRequestMapper employeeRequestMapper;

    public FoodCourtAdapter(IFoodCourtFeignClient foodCourtFeignClient, IEmployeeRequestMapper employeeRequestMapper) {
        this.foodCourtFeignClient = foodCourtFeignClient;
        this.employeeRequestMapper = employeeRequestMapper;
    }

    @Override
    public void addEmployeeToRestaurant(Long restaurantId, Long employeeId) {
        foodCourtFeignClient.addEmployeeToRestaurant(employeeRequestMapper.toRequest(employeeId, restaurantId));
    }

}
