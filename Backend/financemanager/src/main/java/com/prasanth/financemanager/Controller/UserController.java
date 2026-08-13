package com.prasanth.financemanager.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanth.financemanager.DTO.LoginRequest;
import com.prasanth.financemanager.DTO.LoginResponse;
import com.prasanth.financemanager.DTO.UserRequest;
import com.prasanth.financemanager.Entity.User;
import com.prasanth.financemanager.Repo.UserRepository;
import com.prasanth.financemanager.Service.UserService;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/register")
    public User register(@Valid @RequestBody UserRequest userRequest) {
        User user=new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return userService.register(user);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }    

    @GetMapping("/profile")
    public String getProfile(Authentication authentication) {
       return authentication.getName();
    }
    
}
