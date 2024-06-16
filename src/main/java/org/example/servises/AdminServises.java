package org.example.servises;

import org.example.Main;
import org.example.utiils.Input;

public class AdminServises {
    public static void adminMenu() {
        while (true){
        adminDispleyMenu();
            switch (Input.input_INT("choose:")){
                case 1: CourseServises.courseMenu();
                case 2: GroupServises.groupMenu();
                case 3: MentorServises.mentorMenu();
                case 4: StudentServises.studentMenu();
                case 0: {
                    System.out.println("\n\n\n");
                    Main.startPrograma();}
            }
        }
    }

    private static void adminDispleyMenu() {
        System.out.println("""
                -------------------------------------
                1.COURSE
                2.GROUP
                3.MENTOR
                4.STUDERNT
                
                0.LOG OUT
                """);
    }
}
