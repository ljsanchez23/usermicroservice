package com.foodcourt.UserMicroservice.adapters.driving.dto.request;

public class EmployeeRequest {
    private final String name;
    private final String lastName;
    private final Integer idDocument;
    private final String phone;
    private final String email;
    private final String password;
    private final Long restaurantId;

    public EmployeeRequest(String name, String lastName, Integer idDocument, String phone, String email, String password, Long restaurantId) {
        this.name = name;
        this.lastName = lastName;
        this.idDocument = idDocument;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getIdDocument() {
        return idDocument;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

}
