package org.example.servises;

import org.example.Main;
import org.example.db.DB;
import org.example.entity.Admin;
import org.example.entity.Mentor;
import org.example.entity.Student;
import org.example.utiils.Input;

public class AuthServises {
    public static Object login(String username, String password) {

        Admin admin = DB.admins.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (admin != null) {
            return admin;
        }


        Mentor mentor = DB.mentors.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (mentor != null) {
            return mentor;
        }


        Student student = DB.students.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (student != null) {
            return student;
        }


        return null;
    }

    public static void CheckUser(Object user) {
        if (user instanceof Student) {
            Student student = (Student) user;
            System.out.println("-------------------------------------");
            System.out.printf("Welcome, Student: %s %s %n%n", student.getFirstName(), student.getLastName());

        } else if (user instanceof Mentor) {
            Mentor mentor = (Mentor) user;
            System.out.printf("Welcome, Mentor: %s %s %n", mentor.getFirstName(), mentor.getLastName());
            MentorServises.mentorPanel(mentor);
        } else if (user instanceof Admin) {
            Admin admin = (Admin) user;
            System.out.printf("Welcome, Admin: %s %s %n", admin.getFirstName(), admin.getLastName());
            AdminServises.adminMenu();
        } else {
            System.out.println("‼️Invalid username or password");
            Main.startPrograma();
        }
    }
}
