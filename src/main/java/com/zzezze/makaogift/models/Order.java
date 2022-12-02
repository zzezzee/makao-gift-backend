package com.zzezze.makaogift.models;

import com.zzezze.makaogift.dtos.OrderListDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERTABLE")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String maker;
    private String name;
    private String image;
    private Long quantity;
    private Long totalPrice;
    private String receiver;
    private String address;
    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(Long productId, String maker, String name, String image,
                 Long quantity, Long totalPrice, String receiver, String address, String message) {
        this.productId = productId;
        this.maker = maker;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.receiver = receiver;
        this.address = address;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public static Order fake() {
        return new Order(1L, "제조사", "상품이름", "이미지", 1L, 1000L, "받는 분 성함", "받는 분 주소", "받는 분께 보내는 메세지");
    }

    public OrderListDto toOrderListDto() {
        return new OrderListDto(id, receiver, image, maker, name);
    }
}
