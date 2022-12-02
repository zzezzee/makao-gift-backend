package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.dtos.OrderDto;
import com.zzezze.makaogift.dtos.OrderResultDto;
import com.zzezze.makaogift.services.PostOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private PostOrderService postOrderService;

    public OrderController(PostOrderService postOrderService) {
        this.postOrderService = postOrderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResultDto create(
            @RequestBody OrderDto orderDto
            ) {
        Long id = postOrderService.order(
                orderDto.getProductId(),
                orderDto.getQuantity(),
                orderDto.getReceiver(),
                orderDto.getAddress(),
                orderDto.getMessage()
        );

        return new OrderResultDto(id);
    }
}
