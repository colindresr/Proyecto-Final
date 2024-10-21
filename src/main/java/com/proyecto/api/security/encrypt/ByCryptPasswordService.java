package com.proyecto.api.security.encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

public class ByCryptPasswordService implements PasswordEncryptService{

    private final BCryptPasswordEncoder passwordEncoder;

    public ByCryptPasswordService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean isPasswordMatch(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }

}
