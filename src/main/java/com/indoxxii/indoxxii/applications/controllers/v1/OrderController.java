package com.indoxxii.indoxxii.applications.controllers.v1;

import java.util.Set;

import javax.validation.Valid;

import com.indoxxii.indoxxii.applications.request.order.OrderCreateDto;
import com.indoxxii.indoxxii.applications.request.order.OrderUpdateDto;
import com.indoxxii.indoxxii.applications.response.GlobalResponse;
import com.indoxxii.indoxxii.applications.response.Response;
import com.indoxxii.indoxxii.global.Routes;
import com.indoxxii.indoxxii.presist.models.Order;
import com.indoxxii.indoxxii.presist.usecases.OrderUseCase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderController {
    @Autowired
    private OrderUseCase useCase;

    private Response response=new Response();

    
    @GetMapping(Routes.API_V1 + Routes.ORDER)
    public ResponseEntity<GlobalResponse> index() {
            Set<Order>orders = useCase.findAll();
            return response.buildV1(orders, "Ok");
    }
    @GetMapping(Routes.API_V1+Routes.ORDER+Routes.ID)
    public ResponseEntity<GlobalResponse> findById(@PathVariable("id") Integer idorders){
            Order order=useCase.findById(idorders);
            return response.buildV1(order,"Ok");
    }

    @PostMapping(Routes.API_V1+Routes.ORDER)
    public ResponseEntity<GlobalResponse> create(@Valid @RequestBody OrderCreateDto orderCreateDto) throws Exception {
            var order=useCase.create(orderCreateDto);
            return response.buildV1(order, "Success create Order");
    }

    @PutMapping(Routes.API_V1+Routes.ORDER+Routes.ID)
    public ResponseEntity<GlobalResponse> update(@Valid @RequestBody OrderUpdateDto orderUpdateDto,@PathVariable("id") Integer idorders) throws Exception{
            var order=useCase.update(orderUpdateDto,idorders);
            return response.buildV1(order, "Success update Order");
    }
}
