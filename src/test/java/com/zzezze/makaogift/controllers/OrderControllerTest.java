package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.ProductRepository;
import com.zzezze.makaogift.repositories.UserRepository;
import com.zzezze.makaogift.services.GetOrderService;
import com.zzezze.makaogift.services.GetOrdersService;
import com.zzezze.makaogift.services.PostOrderService;
import com.zzezze.makaogift.utils.JwtUtil;
import org.junit.jupiter.api.Test;
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
    private GetOrderService getOrderService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        Order order = Order.fake();
        String username = "zzezze";

        given(getOrdersService.list(username))
                .willReturn(List.of(order.toOrderListDto()));

        String token = jwtUtil.encode(username);

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("orders")
                ));

        verify(getOrdersService).list(username);
    }

    @Test
    void item() throws Exception {
        Order order = Order.fake();

        given(getOrderService.item(1L))
                .willReturn(order.toOrderItemDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("image")
                ));
    }
    
    @Test
    void createOrder() throws Exception {
        Order order = Order.fake();
        String username = "zzezze";

        given(getOrdersService.list(username))
                .willReturn(List.of(order.toOrderListDto()));

        String token = jwtUtil.encode(username);

        given(productRepository.findById(1L))
                .willReturn(Optional.of(Product.fake()));

        mockMvc.perform(MockMvcRequestBuilders.post("/orders")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                " \"productId\":\"1\"," +
                                " \"quantity\":\"1\"," +
                                " \"receiver\":\"받는사람\"," +
                                " \"address\":\"address\"," +
                                " \"message\":\"message\"" +
                                "}")
                )
                .andExpect(status().isCreated());
    }
}
