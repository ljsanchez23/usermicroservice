package com.foodcourt.UserMicroservice.domain.exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String message){
        super(message);
    }
}
