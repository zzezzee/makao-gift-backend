package com.zzezze.makaogift.dtos;

import java.util.List;

public class OrdersDto {
    private List<OrderListDto> orders;

    public OrdersDto(List<OrderListDto> orders) {
        this.orders = orders;
    }

    public List<OrderListDto> getOrders() {
        return orders;
    }
}
