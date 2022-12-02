package com.zzezze.makaogift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDERTABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String maker;
    private String name;
    private String image;
    private Long totalPrice;
    private String receiver;
    private String address;
    private String message;

    public Order() {
    }

    public Order(String maker, String name, String image,
                 Long totalPrice, String receiver, String address, String message) {
        this.maker = maker;
        this.name = name;
        this.image = image;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public Long getId() {
        return id;
    }
}
