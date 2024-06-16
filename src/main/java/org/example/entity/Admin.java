package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enums.Role;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @Builder.Default
    private Role role = Role.ROLE_ADMIN;
}
