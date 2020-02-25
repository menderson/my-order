package com.example.myorder.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.myorder.api.RestPath;
import com.example.myorder.api.dtos.CreateOrderDto;
import com.example.myorder.api.dtos.CreateProductDto;
import com.example.myorder.api.dtos.OrderResponseDto;
import com.example.myorder.api.dtos.ProductResponseDto;
import com.example.myorder.services.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController("OrderController")
@RequestMapping(RestPath.BASE_PATH + "/orders")
@Api(tags = "Pedidos")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    @ApiResponses({
            @ApiResponse(code = 201, message = "Pedido criado!", response = OrderResponseDto.class)
    })
    public OrderResponseDto create(@RequestBody @Valid CreateOrderDto createOrderDto) {
        return orderService.create(createOrderDto);
    }
}
