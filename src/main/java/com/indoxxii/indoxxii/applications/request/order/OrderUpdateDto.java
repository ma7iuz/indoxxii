package com.indoxxii.indoxxii.applications.request.order;

import javax.validation.constraints.NotNull;

import com.indoxxii.indoxxii.presist.models.OrderStatus;

import lombok.Data;

@Data
public class OrderUpdateDto {
    @NotNull
    private OrderStatus orderstatus;
}
