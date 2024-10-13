package com.foodcourt.UserMicroservice.adapters.driven.token.util;

public class JwtRoleNotFoundException extends RuntimeException{
    public JwtRoleNotFoundException(String message){
        super(message);
    }
}
