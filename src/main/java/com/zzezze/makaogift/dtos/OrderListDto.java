package com.zzezze.makaogift.dtos;

public class OrderListDto {
    private Long id;
    private String receiver;
    private String image;
    private String maker;
    private String name;

    public OrderListDto() {
    }

    public OrderListDto(Long id, String receiver, String image, String maker, String name) {
        this.id = id;
        this.receiver = receiver;
        this.image = image;
        this.maker = maker;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getReceiver() {
        return receiver;
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
}
