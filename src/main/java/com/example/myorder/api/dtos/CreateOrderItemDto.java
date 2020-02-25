package com.example.myorder.api.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class CreateOrderItemDto {

    @NotNull
    @ApiModelProperty(value = "${orderItem.create.productId}")
    private Integer productId;

    @NotNull
    @Min(value = 1)
    @ApiModelProperty(value = "${orderItem.create.quantity}")
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public CreateOrderItemDto setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public CreateOrderItemDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
