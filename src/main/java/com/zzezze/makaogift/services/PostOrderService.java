package com.zzezze.makaogift.services;

import com.zzezze.makaogift.exceptions.ProductNotFound;
import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.repositories.OrderRepository;
import com.zzezze.makaogift.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class PostOrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public PostOrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Long order(Long productId, Long quantity, String receiver,
                      String address, String message) {
        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);

        Long totalPrice = product.getPrice() * quantity;
        // TODO: 잔액확인, 잔액 줄이기

        Order order = new Order(
                product.getMaker(), product.getName(), product.getImage(),
                totalPrice, receiver, address, message
        );

        orderRepository.save(order);

        Long id = order.getId();

        return id;
    }
}
