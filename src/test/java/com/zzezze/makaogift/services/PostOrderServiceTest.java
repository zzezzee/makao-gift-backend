package com.zzezze.makaogift.services;

import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.OrderRepository;
import com.zzezze.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PostOrderServiceTest {
    private PostOrderService postOrderService;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);
        postOrderService = new PostOrderService(orderRepository, productRepository);
    }

    @Test
    void order() {
        Long productId = 1L;
        Long quantity = 1L;
        String receiver = "홍길동";
        String address = "서울시";
        String message = "보내는메세지";

        given(productRepository.findById(productId))
                .willReturn(Optional.of(Product.fake()));

        postOrderService.order(productId, quantity, receiver, address, message);

        //TODO 계정에서 돈이 빠져나갔는지 확인

        verify(orderRepository).save(any());
    }
}
