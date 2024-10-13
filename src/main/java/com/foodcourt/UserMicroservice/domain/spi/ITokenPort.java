package com.foodcourt.UserMicroservice.domain.spi;

public interface ITokenPort {
    String getToken(String email);
}
