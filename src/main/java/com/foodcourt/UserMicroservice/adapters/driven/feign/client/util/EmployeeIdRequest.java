package com.foodcourt.UserMicroservice.adapters.driven.feign.client.util;

public class EmployeeIdRequest {
    private Long employeeId;
    private Long restaurantId;

    public EmployeeIdRequest(Long employeeId, Long restaurantId) {
        this.employeeId = employeeId;
        this.restaurantId = restaurantId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
