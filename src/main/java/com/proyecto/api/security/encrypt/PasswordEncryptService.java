package com.proyecto.api.security.encrypt;

public interface PasswordEncryptService {

    String encrypt(String password);
    boolean isPasswordMatch(String password,String encryptedPassword);
}
