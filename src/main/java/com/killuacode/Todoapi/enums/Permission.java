package com.killuacode.Todoapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {

    ADMIN_READ("users:read"),
    ADMIN_DELETE("users:delete"),
    USER_READ("todos:read"),
    USER_WRITE("todos:write"),
    USER_UPDATE("todos:update"),
    USER_DELETE("todos:delete");


    private final String permission;
}
