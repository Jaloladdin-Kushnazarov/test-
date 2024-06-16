package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enums.Role;

import java.util.List;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private UUID id = UUID.randomUUID();
    private String name;
    private List<Group> groups;
}
