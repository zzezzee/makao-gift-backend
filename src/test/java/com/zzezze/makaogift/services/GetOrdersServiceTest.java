package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.OrderListDto;
import com.zzezze.makaogift.dtos.OrdersDto;
import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrdersServiceTest {
    private GetOrdersService getOrdersService;
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        getOrdersService = new GetOrdersService(orderRepository);
    }

    @Test
    void list() {
        Page<Order> orders = Page.empty();

        String username = "zzezze";

        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(0, 8, sort);

        given(orderRepository.findAllByUsername(username, pageable))
                .willReturn(orders);

        OrdersDto ordersDto = getOrdersService.list(username, 1);

        assertThat(ordersDto).isNotNull();
    }
}
