package com.foodcourt.UserMicroservice.domain.spi;

public interface IFoodCourtPort {
    void addEmployeeToRestaurant(Long restaurantId, Long employeeId);
}
