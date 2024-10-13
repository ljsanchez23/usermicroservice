package com.foodcourt.UserMicroservice.adapters.driving.dto.request;

import java.time.LocalDate;

public class UserRequest {
    private final String name;
    private final String lastName;
    private final Integer idDocument;
    private final String phone;
    private final LocalDate dateOfBirth;
    private final String email;
    private final String password;

    public UserRequest(String name, String lastName, Integer idDocument, String phone, LocalDate dateOfBirth, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.idDocument = idDocument;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
