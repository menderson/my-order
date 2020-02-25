package com.example.myorder.api.dtos;

import java.util.List;

import com.example.myorder.enums.OrderStatusEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Resposta de criação da Order")
public class OrderResponseDto {

    @ApiModelProperty(value = "${order.response.id}")
    private Integer id;

    @ApiModelProperty(value = "${order.response.value}")
    private Double totalValue;

    @ApiModelProperty(value = "${order.response.orderStatus}")
    private OrderStatusEnum orderStatus;

    @ApiModelProperty(value = "${order.response.userResponse}")
    private UserResponseDto userResponse;

    @ApiModelProperty(value = "${order.response.itens}")
    private List<OrderItemDto> itens;

    public Integer getId() {
        return id;
    }

    public OrderResponseDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public OrderResponseDto setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public OrderResponseDto setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public UserResponseDto getUserResponse() {
        return userResponse;
    }

    public OrderResponseDto setUserResponse(UserResponseDto userResponse) {
        this.userResponse = userResponse;
        return this;
    }

    public List<OrderItemDto> getItens() {
        return itens;
    }

    public OrderResponseDto setItens(List<OrderItemDto> itens) {
        this.itens = itens;
        return this;
    }
}
