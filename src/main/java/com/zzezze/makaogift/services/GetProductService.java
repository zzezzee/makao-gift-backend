package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.exceptions.ProductNotFound;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class GetProductService {
    private ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto item(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFound::new);

        return product.toDto();
    }
}
