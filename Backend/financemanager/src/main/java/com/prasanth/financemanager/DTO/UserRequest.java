package com.prasanth.financemanager.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    @NotBlank(message = "Please enter valid name")
    private String name;
    @Email(message="Enter a valid Email")
    private String email;
    @Size(min=5,message = "Password should be atleast 5 characters")
    private String password;
}
