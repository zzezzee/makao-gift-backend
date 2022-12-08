package com.zzezze.makaogift.repositories;

import com.zzezze.makaogift.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByUsername(String username, Pageable pageable);

    Order save(Order order);
}
