package com.killuacode.Todoapi.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.killuacode.Todoapi.enums.Permission.*;

@RequiredArgsConstructor
@Getter
public enum Role {

    USER(
            Set.of(
                    USER_READ,
                    USER_WRITE,
                    USER_UPDATE,
                    USER_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    USER_READ,
                    USER_WRITE,
                    USER_UPDATE,
                    USER_DELETE,
                    ADMIN_READ,
                    ADMIN_DELETE
            )
    );


    private final Set<Permission> permissions;

    public Set<? extends GrantedAuthority> getAuthorities() {
        var authorities = permissions
                            .stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                            .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return authorities;
    }

}
