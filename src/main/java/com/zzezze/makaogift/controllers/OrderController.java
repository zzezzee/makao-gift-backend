package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.dtos.OrderPostDto;
import com.zzezze.makaogift.dtos.OrderPostResultDto;
import com.zzezze.makaogift.dtos.OrderListDto;
import com.zzezze.makaogift.dtos.OrdersDto;
import com.zzezze.makaogift.services.GetOrdersService;
import com.zzezze.makaogift.services.PostOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private PostOrderService postOrderService;
    private GetOrdersService getOrdersService;

    public OrderController(PostOrderService postOrderService, GetOrdersService getOrdersService) {
        this.postOrderService = postOrderService;
        this.getOrdersService = getOrdersService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrdersDto list() {
        List<OrderListDto> orderListDto = getOrdersService.list();

        return new OrdersDto(orderListDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPostResultDto create(
            @RequestBody OrderPostDto orderPostDto
            ) {
        Long id = postOrderService.order(
                orderPostDto.getProductId(),
                orderPostDto.getQuantity(),
                orderPostDto.getReceiver(),
                orderPostDto.getAddress(),
                orderPostDto.getMessage()
        );

        return new OrderPostResultDto(id);
    }
}

// OrderDto -> post요청이 담겨 있음
// OrderPostResultDto -> post 요청에 대한 응답


// -> GET / orders 요청에 대한 응답 DTO -> OrdersDto(OrderListDtos)
// -> GET / orders/{id} 요청에 대한 응답 DTO -> OrderDetailDto(세부정보)
