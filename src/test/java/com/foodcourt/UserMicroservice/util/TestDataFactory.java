package com.foodcourt.UserMicroservice.util;

import com.foodcourt.UserMicroservice.domain.model.Role;
import com.foodcourt.UserMicroservice.domain.model.User;
import com.foodcourt.UserMicroservice.domain.util.DefaultRoles;


public class TestDataFactory {

    public static User createDefaultUser() {
        return new User(TestConstants.USER_ID, TestConstants.USER_NAME, TestConstants.USER_LAST_NAME, TestConstants.USER_ID_DOCUMENT, TestConstants.USER_PHONE,
                TestConstants.USER_DOB, TestConstants.USER_EMAIL, TestConstants.USER_PASSWORD, null);
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
