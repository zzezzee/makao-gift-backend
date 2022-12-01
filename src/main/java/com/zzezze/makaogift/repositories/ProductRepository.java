package com.zzezze.makaogift.repositories;

import com.zzezze.makaogift.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
