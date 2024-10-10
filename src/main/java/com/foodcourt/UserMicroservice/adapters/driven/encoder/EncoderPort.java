package com.foodcourt.UserMicroservice.adapters.driven.encoder;

import com.foodcourt.UserMicroservice.domain.spi.IEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderPort implements IEncoderPort {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EncoderPort(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword){
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
