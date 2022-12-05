package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.OrderListDto;
import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetOrdersService {
    private OrderRepository orderRepository;

    public GetOrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderListDto> list(String username) {
        List<Order> orders = orderRepository.findAllByUsername(username);

        List<OrderListDto> ordersResultDto =
                orders.stream()
                        .map(order -> order.toOrderListDto())
                        .collect(Collectors.toList());

        return ordersResultDto;
    }
}
