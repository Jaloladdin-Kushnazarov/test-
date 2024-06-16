package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String name;
    private Mentor mentor;
    private TimeTable timeTable;
    @Builder.Default
    private List<Student> students = new ArrayList<>(30);

    public void addStudent(Student student) {
        if (students.size() < 30) {
            students.add(student);
        } else {
            throw new IllegalStateException("Guruh to'la, 30 dan ortiq talabani qabul qilib bo'lmaydi.");
        }
    }
}