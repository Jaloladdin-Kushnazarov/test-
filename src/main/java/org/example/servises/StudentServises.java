package org.example.servises;

import org.example.db.DB;
import org.example.entity.Group;
import org.example.entity.Student;
import org.example.utiils.Input;

import java.util.ArrayList;
import java.util.List;

public class StudentServises {
    public static void studentMenu() {
        while (true) {
            System.out.println("""
                    --------------------------------
                    1.ADD STUDENT
                    2.INFO STUDENT
                    
                    0.BACK
                    --------------------------------
                    """);
            switch (Input.input_INT("choose:")) {
                case 1: StudentServises.addStudent();
                case 2: StudentServises.infoStudent();
                case 0: AdminServises.adminMenu();
            }
        }
    }


    private static void addStudent() {
        System.out.println("----------ADD Student PANEL ----------------");
        Student student = Student.builder()
                .firstName(Input.input_STRING("Enter student's first name: "))
                .lastName(Input.input_STRING("Enter student's last name: "))
                .email(Input.input_STRING("Enter email adress: "))
                .username(Input.input_STRING("Enter username: "))
                .password(Input.input_STRING("Enter password: "))
                .attandance("  ")
                .build();
        DB.students.add(student);
        System.out.println("✅Students added successfully.");
    }

    private static void infoStudent() {
        int n = 1;
        for (Student student : DB.students) {
            System.out.printf("%d.%s %s ---- group: %s, mentor: %s %n", n++, student.getFirstName(), student.getLastName(), findStudentGroup(student), findStudentMentor(findStudentGroup(student)));
        }
        studentMenu();
    }



    private static String findStudentMentor(String studentGroup) {
        for (Group group : DB.groups) {
            if(group.getName().equals(studentGroup)){
                return group.getMentor().getFirstName() + " " + group.getMentor().getLastName();
            }
        }
        return "";
    }

    private static String findStudentGroup(Student student) {
        for (Group group : DB.groups) {
            if(group.getStudents().contains(student)){
                return group.getName();
            }
        }
        return "";
    }


    public static List<Student> chooseStudent(List<Student> groupStudents) {
        // DB'dagi barcha talabalar ro'yxatini olish va guruhda mavjud bo'lgan talabalarni olib tashlash
        List<Student> availableStudents = new ArrayList<>(DB.students);
        availableStudents.removeAll(groupStudents);

        if (availableStudents.isEmpty()) {
            System.out.println("❌ There are no students available to add.");
            return new ArrayList<>();
        }

        List<Student> selectedStudents = new ArrayList<>();

        while (true) {
            int number = 1;

            // Talabalarni ro'yxati bo'sh bo'lib qolganini tekshirish
            if (availableStudents.isEmpty()) {
                System.out.println("❌ There are no more students available to add.");
                int choice = Input.input_INT("""
                        0. Back
                        Choose: """);
                if (choice >= 0) {
                    break;
                }
            }

            // Mavjud talabalarni ko'rsatish
            for (Student student : availableStudents) {
                System.out.println(number++ + ". " + student.getFirstName() + " " + student.getLastName());
            }

            int index = Input.input_INT("Choose student: ") - 1;
            if (index >= 0 && index < availableStudents.size()) {
                Student selectedStudent = availableStudents.get(index);
                selectedStudents.add(selectedStudent);
                availableStudents.remove(selectedStudent);

                System.out.println("✅ Student '" + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + "' added.");

                int choosePosition = Input.input_INT("""
                        🔴 Do you want to add another student?
                                            
                        1. YES
                        2. NO
                        Choose: """);
                if (choosePosition != 1) {
                    break;
                }
            } else {
                System.out.println("Invalid choice. Please choose again.");
            }
        }

        return selectedStudents;
    }
}
