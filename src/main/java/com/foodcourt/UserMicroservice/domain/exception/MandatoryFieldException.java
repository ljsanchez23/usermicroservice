package com.foodcourt.UserMicroservice.domain.exception;

public class MandatoryFieldException extends RuntimeException{
    public MandatoryFieldException(String message){
        super(message);
    }
}
