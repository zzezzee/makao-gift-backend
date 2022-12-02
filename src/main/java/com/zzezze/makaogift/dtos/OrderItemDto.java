package com.zzezze.makaogift.dtos;

import java.time.LocalDateTime;

public class OrderItemDto {
    private String image;
    private String maker;
    private String name;
    private Long quantity;
    private LocalDateTime createdAt;
    private String receiver;
    private String address;
    private String message;

    public OrderItemDto() {
    }

    public OrderItemDto(String image, String maker, String name, Long quantity, LocalDateTime createdAt,
                        String receiver, String address, String message) {
        this.image = image;
        this.maker = maker;
        this.name = name;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public String getMaker() {
        return maker;
    }

    public String getName() {
        return name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }
}
