package com.killuacode.Todoapi.controller;

import com.killuacode.Todoapi.dto.UserDTO;
import com.killuacode.Todoapi.entity.User;
import com.killuacode.Todoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public List<UserDTO> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }

    @DeleteMapping("{userId}")
    @PreAuthorize("hasAuthority('users:delete')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        return userService.deleteUser(userId);
    }


}
