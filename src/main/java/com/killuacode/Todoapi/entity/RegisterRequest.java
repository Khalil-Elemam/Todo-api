package com.killuacode.Todoapi.entity;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class RegisterRequest {


    @NotBlank
    @Pattern(
            regexp = "^[a-zA-Z\\xc0-\\uFFFF]+([ '\\-]?[a-zA-Z\\xC0-\\uFFFF]+){0,2}[.]?$",
            message = "invalid firstName"
    )
    private String firstName;

    @NotBlank
    @Pattern(
            regexp = "^[a-zA-Z\\xc0-\\uFFFF]+([ '\\-]?[a-zA-Z\\xC0-\\uFFFF]+){0,2}[.]?$",
            message = "invalid firstName"
    )
    private String lastName;

    @Email(message = "invalid email")
    @NotNull(message = "email can't be null")
    private String email;


    private String password;

}
