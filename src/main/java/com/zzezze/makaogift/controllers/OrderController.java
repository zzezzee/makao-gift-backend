package com.zzezze.makaogift.controllers;

import com.zzezze.makaogift.dtos.OrderItemDto;
import com.zzezze.makaogift.dtos.OrderPostDto;
import com.zzezze.makaogift.dtos.OrderPostResultDto;
import com.zzezze.makaogift.dtos.OrderListDto;
import com.zzezze.makaogift.dtos.OrdersDto;
import com.zzezze.makaogift.services.GetOrderService;
import com.zzezze.makaogift.services.GetOrdersService;
import com.zzezze.makaogift.services.PostOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final PostOrderService postOrderService;
    private final GetOrdersService getOrdersService;
    private final GetOrderService getOrderService;

    public OrderController(PostOrderService postOrderService, GetOrdersService getOrdersService, GetOrderService getOrderService) {
        this.postOrderService = postOrderService;
        this.getOrdersService = getOrdersService;
        this.getOrderService = getOrderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public OrdersDto list(
            @RequestAttribute("username") String username
    ) {
        List<OrderListDto> orderListDto = getOrdersService.list(username);

        return new OrdersDto(orderListDto);
    }

    @GetMapping("{id}")
    public OrderItemDto item(
            @PathVariable Long id
    ) {
        OrderItemDto orderItemDto = getOrderService.item(id);

        return orderItemDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderPostResultDto create(
            @RequestBody OrderPostDto orderPostDto,
            @RequestAttribute("username") String username
    ) {
        Long id = postOrderService.order(
                username,
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
