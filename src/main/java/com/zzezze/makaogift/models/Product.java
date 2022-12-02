package com.zzezze.makaogift.models;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.dtos.ProductsDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private String maker;

    private String description;

    private String image;

    public Product() {
    }

    public Product(Long id, String name, Long price, String maker, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maker = maker;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getMaker() {
        return maker;
    }

    public Long getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public static Product fake() {
        return new Product(1L, "상품이름1", 10000L, "제조사1", "상품설명", "");
    }

    public ProductDto toDto() {
        return new ProductDto(id, name, price, maker, description, image);
    }
}
