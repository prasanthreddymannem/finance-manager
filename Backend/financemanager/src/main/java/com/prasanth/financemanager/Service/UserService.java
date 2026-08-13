package com.prasanth.financemanager.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prasanth.financemanager.DTO.LoginRequest;
import com.prasanth.financemanager.DTO.LoginResponse;
import com.prasanth.financemanager.Entity.User;
import com.prasanth.financemanager.Repo.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,JWTService jwtService){
        this.userRepo=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }
    public User register(User user){
        if(userRepo.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
    public LoginResponse login(LoginRequest loginRequest){
        User user=userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()->
           new RuntimeException("User not Found"));
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new RuntimeException("Login Failed incorrect Password");
        }
        String token=jwtService.generateToken(user);
        return new LoginResponse(token);
    }
}
