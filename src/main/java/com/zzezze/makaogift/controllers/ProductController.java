package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.dtos.ProductsDto;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ProductsDto list() {
        List<ProductDto> productDtos = productService.list();

        return new ProductsDto(productDtos);
    }
}
