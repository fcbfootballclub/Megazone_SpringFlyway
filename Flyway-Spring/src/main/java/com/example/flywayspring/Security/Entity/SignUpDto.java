package com.example.flywayspring.Security.Entity;

import lombok.Data;

@Data
public class SignUpDto {
    private String userName;
    private String password;
    private String email;

}
