package com.killuacode.Todoapi.service;


import com.killuacode.Todoapi.entity.AuthenticationRequest;
import com.killuacode.Todoapi.entity.AuthenticationResponse;
import com.killuacode.Todoapi.entity.RegisterRequest;
import com.killuacode.Todoapi.entity.User;
import com.killuacode.Todoapi.enums.Role;
import com.killuacode.Todoapi.exception.EmailAlreadyTaken;
import com.killuacode.Todoapi.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {

        boolean isEmailTaken = userRepository.findByEmail(request.getEmail()).isPresent();

        if(isEmailTaken)
            throw new EmailAlreadyTaken("Email is already taken");

        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        String accessToken = jwtService.generateAccessToken(user);
        logger.info("the token for the new user is {}", accessToken);
        userRepository.save(user);
        AuthenticationResponse authenticationResponse = AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .build();
        logger.info("the authentication response is {}", authenticationResponse);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        String accessToken = jwtService.generateAccessToken(user);
        AuthenticationResponse authenticationResponse = AuthenticationResponse
                .builder()
                .accessToken(accessToken)
                .build();
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
