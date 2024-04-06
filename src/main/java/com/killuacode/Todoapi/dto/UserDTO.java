package com.killuacode.Todoapi.dto;

import com.killuacode.Todoapi.enums.Role;
import lombok.Builder;

@Builder
public record UserDTO(
        Integer id,
        String firstName,
        String lastName,
        String email,
        Role role
) {
}
