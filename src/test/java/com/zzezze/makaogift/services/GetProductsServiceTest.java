package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductsServiceTest {
    ProductRepository productRepository;
    GetProductsService getProductsService;

    @BeforeEach
    void setup() {
        productRepository = mock(ProductRepository.class);
        getProductsService = new GetProductsService(productRepository);
    }

    @Test
    void list() {
        List<Product> products = List.of(Product.fake());

        given(productRepository.findAll()).willReturn(products);

        List<ProductDto> productDtos = getProductsService.list();

        assertThat(productDtos).hasSize(1);
    }
}
