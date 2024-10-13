package com.foodcourt.UserMicroservice.domain.exception;

public class InvalidDateOfBirthException extends RuntimeException{
    public InvalidDateOfBirthException(String message){
        super(message);
    }
}
