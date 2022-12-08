package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.OrderListDto;
import com.zzezze.makaogift.dtos.OrdersDto;
import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetOrdersService {
    private final OrderRepository orderRepository;

    public GetOrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrdersDto list(String username, Integer page) {
        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(page -1, 8, sort);

        Page<Order> orders = orderRepository.findAllByUsername(username, pageable);

        Long pageCount = (long) orders.getTotalPages();

        List<OrderListDto> ordersResultDto =
                orders.stream()
                        .map(order -> order.toOrderListDto())
                        .collect(Collectors.toList());

        return new OrdersDto(ordersResultDto, pageCount);
    }
}
