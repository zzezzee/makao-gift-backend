package com.zzezze.makaogift.services;

import com.zzezze.makaogift.dtos.ProductDto;
import com.zzezze.makaogift.dtos.ProductsDto;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetProductsService {
    private final ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductsDto list(int page) {
        Sort sort = Sort.by("id");
        Pageable pageable = PageRequest.of(page -1, 8, sort);

        Page<Product> products = productRepository.findAll(pageable);

        Long pageCount = (long) products.getTotalPages();

        List<ProductDto> productDtos = products.stream()
                .map(Product::toDto)
                .collect(Collectors.toList());

        return new ProductsDto(productDtos, pageCount);
    }
}
