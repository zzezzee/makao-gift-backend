package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.dtos.ProductsDto;
import com.zzezze.makaogift.services.GetProductService;
import com.zzezze.makaogift.services.GetProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private GetProductsService getProductsService;
    private GetProductService getProductService;

    public ProductController(GetProductsService getProductsService, GetProductService getProductService) {
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
    }

    @GetMapping
    public ProductsDto list(
            @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        ProductsDto productsDto = getProductsService.list(page);

        return productsDto;
    }

    @GetMapping("/{id}")
    public ProductDto item(
            @PathVariable Long id
    ) {
        ProductDto productDto = getProductService.item(id);

        return productDto;
    }
}
