package org.example.servises;

import org.example.db.DB;
import org.example.entity.Course;
import org.example.entity.Group;
import org.example.utiils.Input;

import java.util.ArrayList;
import java.util.List;

public class CourseServises {

    public static void courseMenu() {
        while (true) {
            System.out.println("""
                    --------------------------------
                    1.ADD COURSE
                    2.INFO COURSE
                                        
                    0.BACK
                    --------------------------------
                    """);
            switch (Input.input_INT("choose: ")) {
                case 1:
                    addCourse();
                case 2:
                    infoCourse();
                case 0:
                    AdminServises.adminMenu();
            }
        }
    }


    public static void addCourse() {
        System.out.println("----------ADD COURSE PANEL ----------------");
        Course course = Course.builder()
                .name(Input.input_STRING("Enter Course Name: "))
                .groups(new ArrayList<>())
                .build();


        int temp = Input.input_INT("""
                Do you want to add a Group?
                                
                1. YES
                2. NO
                choose: 
                """);

        if (temp == 1) {

            List<Group> tempGroups = GroupServises.chooseGroup(course.getGroups());
            course.getGroups().addAll(tempGroups);
            System.out.println("Groups added successfully.");
            DB.courses.add(course);
            System.out.println("✅ Successfully added course with groups.");
            courseMenu();
        } else {

            DB.courses.add(course);
            System.out.println("✅ Successfully added course with groups.");
            courseMenu();

        }
    }


    private static void infoCourse() {
        System.out.println("----------INFO COURSE PANEL ----------------");
        System.out.println("Choose Course: ");
        for (int i = 0; i < DB.courses.size(); i++) {
            System.out.println(i + 1 + ". " + DB.courses.get(i).getName());
        }
        System.out.println("\n0.BACK");
        int index = Input.input_INT("choose: ");
        if (index == 0) {
            courseMenu();
        }
        Course course = DB.courses.get(index - 1);
        System.out.println("Course Name: " + course.getName());
        System.out.println("Course Groups: ");
        for (int i = 0; i < course.getGroups().size(); i++) {
            System.out.println(i + 1 + ". " + course.getGroups().get(i).getName());
        }
        System.out.println("--------------------------------");
    }

    public static Course selectCourse() {
        int i = 1;
        for (Course cours : DB.courses) {
            System.out.println(i++ + "." + cours.getName());
        }
        int index = Input.input_INT("Choose course: ") - 1;
        return DB.courses.get(index);
    }
}
