package org.example.entity;

import lombok.*;
import org.example.entity.enums.Role;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String attandance = "  ";
    @Builder.Default
    private Role role = Role.ROLE_STUDENT;
}