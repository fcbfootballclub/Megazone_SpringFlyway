package com.example.flywayspring.Security.Entity;

import lombok.Data;

@Data
public class LoginDTO {
    private String userNameOrEmail;
    private String password;
}
