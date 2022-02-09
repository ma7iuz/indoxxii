package com.indoxxii.indoxxii.applications.handler;

import com.indoxxii.indoxxii.applications.exception.OrderAlreadyPaidException;
import com.indoxxii.indoxxii.applications.response.GlobalResponse;
import com.indoxxii.indoxxii.applications.response.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {
    
        @ExceptionHandler({ OrderAlreadyPaidException.class})
        public ResponseEntity<GlobalResponse> handleOrderAlreadyPaidException(
                Exception ex, WebRequest request) {
            Response response=new Response();
            return response.errorV1(ex.getMessage(), "500");
        }
}
