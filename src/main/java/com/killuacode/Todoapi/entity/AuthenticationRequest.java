package com.killuacode.Todoapi.entity;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class AuthenticationRequest {

    @Email
    @NotNull
    private String email;
    private String password;
}
