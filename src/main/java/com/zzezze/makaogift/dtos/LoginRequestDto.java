package com.zzezze.makaogift.dtos;

public class LoginRequestDto {
    private String username;

    private String password;

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
