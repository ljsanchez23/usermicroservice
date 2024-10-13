package com.foodcourt.UserMicroservice.adapters.util;

public class AdaptersConstants {
    public static final String USER_CONTROLLER_URL = "/user";
    public static final String GET_USER_BY_ID_ENDPOINT_URL = "/{id}";
    public static final String USER_TABLE_NAME = "user";
    public static final String ROLE_TABLE_NAME = "role";
    public static final String USER_ROLE_JOIN_COLUMN = "role_id";
    public static final String ENDPOINT_SUMMARY = "Create user";
    public static final String ENDPOINT_DESCRIPTION = "Creates an user in the system";
    public static final String CREATED = "201";
    public static final String BAD_REQUEST = "400";
    public static final String JSON = "application/json";
    public static final String CREATED_DESCRIPTION = "The user has been successfully created";
    public static final String BAD_REQUEST_DESCRIPTION = "The user has not been successfully created";
    public static final String ID_PATH_VARIABLE = "id";
    public static final String LOGIN_URL = "/login";
    public static final String AUTHENTICATION_ENDPOINT_SUMMARY = "This is the endpoint for the authentication";
    public static final String AUTHENTICATION_ENDPOINT_DESCRIPTION = "This enpoint can be used to access the system";
    public static final String OK = "200";
    public static final String AUTHENTICATION_OK_DESCRIPTION = "If the authentication is successful a 200 response code" +
            "will be given";
    public static final String AUTHENTICATION_BAD_REQUEST_DESCRIPTION = "If the authentication does not got through a 400" +
            "code will be given";
}
