package com.indoxxii.indoxxii.applications.response;


import lombok.Data;

@Data
public class GlobalResponse {
    private Integer status;
    private String message;
    private String code;

}
