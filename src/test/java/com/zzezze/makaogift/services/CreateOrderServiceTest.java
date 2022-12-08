package com.zzezze.makaogift.services;

import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.repositories.OrderRepository;
import com.zzezze.makaogift.repositories.ProductRepository;
import com.zzezze.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateOrderServiceTest {
    private CreateOrderService postOrderService;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);
        userRepository = mock(UserRepository.class);
        postOrderService = new CreateOrderService(orderRepository, productRepository, userRepository);
    }

    @Test
    void order() {
        Long productId = 1L;
        Long quantity = 1L;
        String username = "zzezze";
        String receiver = "홍길동";
        String address = "서울시";
        String message = "보내는메세지";

        given(productRepository.findById(productId))
                .willReturn(Optional.of(Product.fake()));

        User user = User.fake(username);

        given(userRepository.findByUsername("zzezze"))
                .willReturn(Optional.of(user));

        postOrderService.order(username, productId, quantity, receiver, address, message);

        assertThat(user.amount() < 50000L).isTrue();

        verify(orderRepository).save(any());
    }
}
