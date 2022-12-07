package com.zzezze.makaogift.dtos;

import java.util.List;

public class ProductsDto {
    private List<ProductDto> products;
    private Long pageCount;

    public ProductsDto(List<ProductDto> products, Long pageCount) {
        this.products = products;
        this.pageCount = pageCount;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public Long getPageCount() {
        return pageCount;
    }
}
