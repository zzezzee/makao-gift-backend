package com.zzezze.makaogift.dtos;

public class LoginResultDto {
    private String accessToken;

    private Long amount;

    public LoginResultDto(String accessToken, Long amount) {
        this.accessToken = accessToken;
        this.amount = amount;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Long getAmount() {
        return amount;
    }
}
