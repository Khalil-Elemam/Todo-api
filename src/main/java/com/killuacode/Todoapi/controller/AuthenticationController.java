package com.killuacode.Todoapi.controller;


import com.killuacode.Todoapi.entity.AuthenticationRequest;
import com.killuacode.Todoapi.entity.AuthenticationResponse;
import com.killuacode.Todoapi.entity.RegisterRequest;
import com.killuacode.Todoapi.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return authenticationService.register(request);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest request
    ) {
        return authenticationService.login(request);
    }

}
