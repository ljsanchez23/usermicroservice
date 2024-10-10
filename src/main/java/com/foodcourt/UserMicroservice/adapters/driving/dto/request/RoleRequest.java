package com.foodcourt.UserMicroservice.adapters.driving.dto.request;

public class RoleRequest {
    private Long id;
    private String name;

    public RoleRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
