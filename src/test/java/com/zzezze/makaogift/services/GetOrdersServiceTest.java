package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.OrderListDto;
import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        List<Order> orders = List.of(Order.fake());
        String username = "zzezze";

        given(orderRepository.findAllByUsername(username)).willReturn(orders);

        List<OrderListDto> orderDtos = getOrdersService.list(username);

        assertThat(orderDtos).hasSize(1);
    }
}
