package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<ProductDto> list() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtos =
                products.stream()
                        .map(product -> product.toDto())
                        .collect(Collectors.toList());

        return productDtos;
    }
}
