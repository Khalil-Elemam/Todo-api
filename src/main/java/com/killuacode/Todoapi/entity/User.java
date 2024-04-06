package com.killuacode.Todoapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import com.killuacode.Todoapi.enums.Role;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "_user")
public class User implements UserDetails{

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "invalid firstName")
    @Pattern(regexp = "^[a-zA-z\\xC0-\\uFFFF]+(['\\- ]?[a-zA-z\\xC0-\\uFFFF]+){0,2}[.]?$", message = "invalid firstName")
    private String firstName;

    @NotBlank(message = "invalid lastName")
    @Pattern(regexp = "^[a-zA-Z\\xC0-\\uFFFF]+([' \\-]?[a-zA-Z\\xC0-\\uFFFF]+){0,2}[.]?$", message = "invalid lastName")
    private String lastName;


    @Email(message = "invalid email")
    @NotNull(message = "email is mandatory")
    @Column(unique = true)
    private String email;

    @Size(min = 8, message = "password should be at least 8 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    @ToString.Exclude
    private List<Todo> todoList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }



    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
