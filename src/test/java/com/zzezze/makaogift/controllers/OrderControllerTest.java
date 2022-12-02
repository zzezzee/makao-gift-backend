package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.OrderRepository;
import com.zzezze.makaogift.repositories.ProductRepository;
import com.zzezze.makaogift.services.GetOrdersService;
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

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostOrderService postOrderService;

    @MockBean
    private GetOrdersService getOrdersService;

    @MockBean
    private ProductRepository productRepository;


    @Test
    void list() throws Exception {
        Order order = Order.fake();

        given(getOrdersService.list())
                .willReturn(List.of(order.toOrderResultDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("orders")
                ));

        verify(getOrdersService).list();
    }


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
