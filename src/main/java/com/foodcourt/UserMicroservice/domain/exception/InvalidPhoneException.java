package com.foodcourt.UserMicroservice.domain.exception;

public class InvalidPhoneException extends RuntimeException{
    public InvalidPhoneException(String message){
        super(message);
    }
}
