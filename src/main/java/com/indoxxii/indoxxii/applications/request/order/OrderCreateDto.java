package com.indoxxii.indoxxii.applications.request.order;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.indoxxii.indoxxii.presist.models.OrderStatus;
import com.indoxxii.indoxxii.presist.models.PaymentMethod;

import lombok.Data;

@Data
public class OrderCreateDto {

    @NotNull
    private PaymentMethod paymentmethod;

    @NotNull
    private OrderStatus orderstatus;

    @NotNull
    private Integer users_id;

    @NotNull
    private Set<Integer> movies;
}
