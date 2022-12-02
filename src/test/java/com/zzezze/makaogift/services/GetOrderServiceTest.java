package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.OrderItemDto;
import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetOrderServiceTest {
    private OrderRepository orderRepository;
    private GetOrderService getOrderService;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        getOrderService = new GetOrderService(orderRepository);
    }

    @Test
    void item() {
        Order order = Order.fake();

        given(orderRepository.findById(1L))
                .willReturn(Optional.of(order));

        OrderItemDto orderItemDto = getOrderService.item(1L);

        assertThat(orderItemDto).isNotNull();
    }
}
