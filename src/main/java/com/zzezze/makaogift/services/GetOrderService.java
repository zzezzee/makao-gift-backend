package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.OrderItemDto;
import com.zzezze.makaogift.exceptions.OrderNotFound;
import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.repositories.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class GetOrderService {
    private OrderRepository orderRepository;

    public GetOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderItemDto item(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(OrderNotFound::new);

        return order.toOrderItemDto();
    }
}
