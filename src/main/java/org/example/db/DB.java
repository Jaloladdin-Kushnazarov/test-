package org.example.db;

import org.example.entity.*;
import org.example.servises.TimeTableServices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.example.servises.TimeTableServices.timeTableOfGroup;

public interface DB {

    List<Admin> admins = new ArrayList<>();
    List<Course> courses = new ArrayList<>();
    List<Group> groups = new ArrayList<>();
    List<Mentor> mentors = new ArrayList<>();
    List<Student> students = new ArrayList<Student>();


    public static void generateData() {
        admins.add(
                Admin.builder()
                        .username("admin")
                        .password("123")
                        .firstName("Adminjon")
                        .lastName("Adminov")
                        .email("admin@gmail.com")
                        .build());

        courses.add(Course.builder().name("Backend").build());
        courses.add(Course.builder().name("Frontend").build());


        mentors.add(Mentor.builder()
                .username("mentor1")
                .password("123")
                .firstName("Eshmat")
                .lastName("Eshmatov")
                .email("eshmat@gmail.com")
                .build());

        students.add(Student.builder()
                .username("student1")
                .password("123")
                .firstName("Toshmat")
                .lastName("Toshmatov")
                .email("toshmat@gmail.com")
                        .attandance("  ")
                .build());

        students.add(Student.builder()
                .username("student2")
                .password("123")
                .firstName("Jaloladdin")
                .lastName("Qoshnazarov000")
                .email("toshmat@gmail.com")
                        .attandance("  ")
                .build());

        TimeTable timeTable = TimeTable.builder()
                .lessonDates(timeTableOfGroup(LocalDate.now()))
                .build();

        groups.add(Group.builder()
                .name("G40")
                .mentor(mentors.getFirst())
                .students(students)
                .timeTable(timeTable)
                .build());

        groups.add(Group.builder()
                .name("G41")
                .mentor(mentors.getFirst())
                .timeTable(timeTable)
                .build());
    }
}
