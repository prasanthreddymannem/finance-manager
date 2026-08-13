package com.prasanth.financemanager.DTO;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
public class LoginRequest {
    @Email(message="Please enter a valid email")
    private String email;
    @Size(min=6,message = "Minimum length for Password is 6")
    private String password;
}
