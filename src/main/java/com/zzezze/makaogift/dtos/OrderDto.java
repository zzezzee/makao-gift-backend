package com.zzezze.makaogift.dtos;

public class OrderDto {
    private Long productId;
    private Long quantity;
    private String receiver;
    private String address;
    private String message;

    public OrderDto(Long productId, Long quantity, String receiver, String address, String message) {
        this.productId = productId;
        this.quantity = quantity;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getQuantity() {
        return quantity;
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
