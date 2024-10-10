package com.foodcourt.UserMicroservice.domain.util;

import com.foodcourt.UserMicroservice.domain.model.Role;

public class DefaultRoles {
    public static final Long ADMIN_ID = 1L;
    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";

    public static final Long OWNER_ID = 2L;
    public static final String OWNER_ROLE_NAME = "ROLE_OWNER";

    public static final Role ADMIN = new Role(ADMIN_ID, ADMIN_ROLE_NAME);
    public static final Role OWNER = new Role(OWNER_ID, OWNER_ROLE_NAME);
}
