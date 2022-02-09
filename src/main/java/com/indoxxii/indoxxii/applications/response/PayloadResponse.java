package com.indoxxii.indoxxii.applications.response;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PayloadResponse extends GlobalResponse{
    private Object data;

    public PayloadResponse(Object data){
        this.data=data;
    }
}
