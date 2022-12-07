package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.dtos.ProductsDto;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(0, 8, sort);
        given(productRepository.findAll(pageable)).willReturn((Page<Product>) products);


        ProductsDto productsDto = getProductsService.list(1);

        assertThat(productsDto).isNotNull();
    }
}
