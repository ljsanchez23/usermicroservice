package com.foodcourt.UserMicroservice.domain.exception;

public class UnderageUserException extends RuntimeException{
    public UnderageUserException(String message){
        super(message);
    }
}
