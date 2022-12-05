package com.zzezze.makaogift.repositories;

import com.zzezze.makaogift.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUsername(String username);

    Order save(Order order);
}
