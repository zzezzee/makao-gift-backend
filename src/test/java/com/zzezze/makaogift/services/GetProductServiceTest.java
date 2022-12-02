package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductServiceTest {
    ProductRepository productRepository;
    GetProductService getProductService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        getProductService = new GetProductService(productRepository);
    }

    @Test
    void item() {
        Product product = Product.fake();

        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        ProductDto productDto = getProductService.item(1L);

        assertThat(productDto).isNotNull();
    }
}
