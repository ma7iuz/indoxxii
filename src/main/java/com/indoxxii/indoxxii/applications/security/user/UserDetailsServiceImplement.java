package com.indoxxii.indoxxii.applications.security.user;

import com.indoxxii.indoxxii.presist.models.User;
import com.indoxxii.indoxxii.presist.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImplement implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new UserDetailsImplement(user);
    }
    
}
