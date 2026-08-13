package com.prasanth.financemanager.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prasanth.financemanager.Entity.User;
import com.prasanth.financemanager.Repo.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService{
    private final UserRepository userRepository;
    public CustomerUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
@Override
public UserDetails loadUserByUsername(String email){
    User user = userRepository.findByEmail(email).orElseThrow(()->
        new UsernameNotFoundException("User not found: "+email));
        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
        .password(user.getPassword()).authorities("USER").build();
}
    
}