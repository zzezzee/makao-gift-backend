package com.zzezze.makaogift.services;

import com.zzezze.makaogift.exceptions.OrderFailed;
import com.zzezze.makaogift.exceptions.ProductNotFound;
import com.zzezze.makaogift.exceptions.UserNotFound;
import com.zzezze.makaogift.models.Order;
import com.zzezze.makaogift.models.Product;
import com.zzezze.makaogift.models.User;
import com.zzezze.makaogift.repositories.OrderRepository;
import com.zzezze.makaogift.repositories.ProductRepository;
import com.zzezze.makaogift.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private UserRepository userRepository;

    public CreateOrderService(OrderRepository orderRepository,
                              ProductRepository productRepository,
                              UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Long order(String username, Long productId, Long quantity, String receiver,
                      String address, String message) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFound());

        Product product = productRepository.findById(productId)
                .orElseThrow(ProductNotFound::new);

        Long totalPrice = product.getPrice() * quantity;

        if(user.amount() < totalPrice){
            throw new OrderFailed();
        }

        user.order(totalPrice);

        Order order = new Order(
                username, productId,
                product.getMaker(), product.getName(), product.getImage(),
                quantity, totalPrice, receiver, address, message);

        orderRepository.save(order);

        Long id = order.getId();

        return id;
    }
}
