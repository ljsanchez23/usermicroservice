package com.foodcourt.UserMicroservice.util;

import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.util.DefaultRoles;

import java.time.LocalDate;

public class TestDataFactory {

    public static User createDefaultUser() {
        return new User(1L, "John without ", "Fear", 1111111111, "3222222",
                LocalDate.of(1998, 12, 23), "test@example.com", "password", null);
    }

    public static User createUserWithoutRole() {
        User user = createDefaultUser();
        user.setRoleId(null);
        return user;
    }

    public static Role createDefaultRole() {
        return new Role(DefaultRoles.OWNER_ID, DefaultRoles.OWNER_ROLE_NAME);
    }
}
