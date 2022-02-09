package com.indoxxii.indoxxii.applications.exception;

public class OrderAlreadyPaidException extends Exception{
    public OrderAlreadyPaidException(){
        super("Order Already Paid");
    }

    public OrderAlreadyPaidException(String message){
        super(message);
    }
}
