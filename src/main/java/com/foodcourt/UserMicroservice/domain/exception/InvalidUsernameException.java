package com.foodcourt.UserMicroservice.domain.exception;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException(String message){
        super(message);
    }
}
