package com.indoxxii.indoxxii.applications.controllers.v1;



import javax.validation.Valid;

import com.indoxxii.indoxxii.applications.request.user.UserSignInDto;
import com.indoxxii.indoxxii.applications.request.user.UserSignUpDto;
import com.indoxxii.indoxxii.applications.response.GlobalResponse;
import com.indoxxii.indoxxii.applications.response.Response;
import com.indoxxii.indoxxii.applications.security.jwt.JwtUtils;
import com.indoxxii.indoxxii.applications.security.user.UserDetailsImplement;
import com.indoxxii.indoxxii.global.Routes;
import com.indoxxii.indoxxii.presist.usecases.UserUseCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserUseCase useCase;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AuthenticationManager authenticationManager;

    Response response=new Response();
    @PostMapping(Routes.API_V1 + Routes.USER+Routes.SIGN_UP)
    public ResponseEntity<GlobalResponse> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto) throws Exception {;
        useCase.signUp(userSignUpDto);
        
        return response.buildV1("Success Create User");
    }

    @PostMapping(Routes.API_V1 + Routes.USER + Routes.SIGN_IN)
    public ResponseEntity<GlobalResponse> signIn(@Valid @RequestBody UserSignInDto userSignInDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userSignInDto.getUsername(), userSignInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImplement userDetails = (UserDetailsImplement) authentication.getPrincipal();

        return response.buildJwtV1(jwt,userDetails.getId(),userDetails.getUsername(),userDetails.getAuthorities().size()+"");
    }

   
}
