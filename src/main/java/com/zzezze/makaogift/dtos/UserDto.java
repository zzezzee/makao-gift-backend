package com.zzezze.makaogift.dtos;

public class UserDto {
    private Long amount;

    public UserDto() {
    }

    public UserDto(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }
}
