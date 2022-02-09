package com.indoxxii.indoxxii.presist.usecases;


import com.indoxxii.indoxxii.applications.request.user.UserSignUpDto;
import com.indoxxii.indoxxii.presist.models.User;
import com.indoxxii.indoxxii.presist.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUseCase {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public User signUp(UserSignUpDto dto) throws Exception{
        var newUser=new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setEmail(dto.getEmail());
        newUser.setBirthdate(dto.getBirthdate());
        return userRepository.save(newUser);

    }
    
}
