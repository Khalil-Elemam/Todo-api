package com.killuacode.Todoapi.exception;


import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionController {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {

        List<String> messages = new ArrayList<>();
        int count = 1;
        for(FieldError fieldError: ex.getFieldErrors())
            messages.add(count++ + ") " + fieldError.getDefaultMessage());

        var error = ErrorResponse
                .builder()
                .messages(messages)
                .error(HttpStatus.BAD_REQUEST.name())
                .status(ex.getStatusCode().value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();

        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    @ExceptionHandler(EmailAlreadyTaken.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyTakenException(EmailAlreadyTaken ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        var error = ErrorResponse
                .builder()
                .messages(List.of("1) " + ex.getMessage()))
                .error(status.name())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTodoNotFoundException(TodoNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        var error = ErrorResponse
                .builder()
                .messages(List.of("1) " + ex.getMessage()))
                .error(status.name())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ErrorResponse> handleJwtExceptions(JwtException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        var error = ErrorResponse
                .builder()
                .error(status.name())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .messages(List.of("1) " + ex.getMessage()))
                .build();
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        var error = ErrorResponse
                .builder()
                .error(status.name())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .messages(List.of("1) " + "Username or password aren't correct"))
                .build();
        return new ResponseEntity<>(error, status);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        var error = ErrorResponse
                .builder()
                .error(status.name())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .messages(List.of("1) " + ex.getMessage()))
                .build();
        return new ResponseEntity<>(error, status);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerExceptions(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        var error = ErrorResponse
                .builder()
                .error(status.name())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .messages(List.of("1) " + ex.getMessage()))
                .build();
        return new ResponseEntity<>(error, status);
    }




}
