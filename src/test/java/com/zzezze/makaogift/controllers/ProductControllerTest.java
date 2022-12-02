package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.services.GetProductService;
import com.zzezze.makaogift.services.GetProductsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductsService getProductsService;

    @MockBean
    private GetProductService getProductService;


    @Test
    void list() throws Exception {
        Product product = Product.fake();

        given(getProductsService.list())
                .willReturn(List.of(product.toDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("products")
                ));

        verify(getProductsService).list();
    }

    @Test
    void item() throws Exception {
        Product product = Product.fake();

        given(getProductService.item(1L))
                .willReturn(product.toDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("10000")
                ));

        verify(getProductService).item(1L);
    }
}
