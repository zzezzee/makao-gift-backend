package com.zzezze.makaogift.dtos;

import java.util.List;

public class OrdersDto {
    private List<OrderListDto> orders;
    private Long pageCount;

    public OrdersDto(List<OrderListDto> orders, Long pageCount) {
        this.orders = orders;
        this.pageCount = pageCount;
    }

    public List<OrderListDto> getOrders() {
        return orders;
    }

    public Long getPageCount() {
        return pageCount;
    }
}
