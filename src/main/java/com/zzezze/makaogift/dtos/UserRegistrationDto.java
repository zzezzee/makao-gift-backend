package com.zzezze.makaogift.dtos;

public class UserRegistrationDto {
    private String name;

    private String username;

    private String password;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
