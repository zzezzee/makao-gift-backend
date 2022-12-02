package com.zzezze.makaogift.repositories;

import com.zzezze.makaogift.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order order);
}
