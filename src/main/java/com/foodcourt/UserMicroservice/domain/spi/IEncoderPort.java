package com.foodcourt.UserMicroservice.domain.spi;

public interface IEncoderPort {
    String encode(String password);
    boolean matches(String rawPassword, String encodedPassword);
}
