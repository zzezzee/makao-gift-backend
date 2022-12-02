package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.OrderRepository;
import com.zzezze.makaogift.repositories.ProductRepository;
import com.zzezze.makaogift.services.PostOrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostOrderService postOrderService;

    @MockBean
    private ProductRepository productRepository;


    @Test
    void createOrder() throws Exception {
        given(productRepository.findById(1L))
                .willReturn(Optional.of(Product.fake()));

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                " \"productId\":\"1\"," +
                                " \"quantity\":\"1\"," +
                                " \"receiver\":\"receiver\"," +
                                " \"address\":\"address\"," +
                                " \"message\":\"message\"" +
                                "}")
                )
                .andExpect(status().isCreated());
    }
}
