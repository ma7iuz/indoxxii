package com.indoxxii.indoxxii.applications.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    @Autowired
    GlobalResponse response;
    public ResponseEntity<GlobalResponse>buildV1(Object data,String message){
        response = new PayloadResponse(data);
        response.setStatus(1);
        response.setMessage(message);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }
    
    public ResponseEntity<GlobalResponse> buildV1(String message,String code){
        response=new GlobalResponse();
        response.setMessage(message);
        response.setStatus(1);
        response.setCode(code);

        return new ResponseEntity<GlobalResponse>(response,HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> buildV1(String message) {
        response = new GlobalResponse();
        response.setMessage(message);
        response.setStatus(1);
        response.setCode(HttpStatus.OK.toString());

        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> buildJwtV1(String token,int id,String username, String message) {
        response = new JwtResponse(token, id, username);
        response.setStatus(1);
        response.setMessage(message);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<GlobalResponse> errorV1(String message, String code) {
        response = new GlobalResponse();
        response.setMessage(message);
        response.setStatus(-1);
        response.setCode(code);

        return new ResponseEntity<GlobalResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<GlobalResponse> errorV1(String message) {
        response = new GlobalResponse();
        response.setMessage(message);
        response.setStatus(-1);
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return new ResponseEntity<GlobalResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
