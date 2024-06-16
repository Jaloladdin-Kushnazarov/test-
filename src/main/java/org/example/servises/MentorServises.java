package org.example.servises;

import org.example.Main;
import org.example.db.DB;
import org.example.entity.Group;
import org.example.entity.Mentor;
import org.example.entity.Student;
import org.example.utiils.Input;

import java.time.LocalDate;


public class MentorServises {
    public static void mentorMenu() {
        while (true){
            System.out.println("""
                    --------------------------------
                    1.ADD MENTOR
                    2.INFO MENTOR
                    
                    0.BACK
                    --------------------------------
                    """);
            switch (Input.input_INT("choose: ")){
                case 1: addMentor();
                case 2: infoMentor();
                case 0: AdminServises.adminMenu();
            }
        }
    }

    private static void infoMentor() {
        int n = 1;
        for (Mentor mentor : DB.mentors) {
            System.out.printf("%d. %s %s - group's list: [", n++, mentor.getFirstName(), mentor.getLastName());
            for (Group group : DB.groups) {
                if(group.getMentor().getId().equals(mentor.getId())){
                    System.out.print(group.getName() + ", ");
                }
            }
            System.out.println("]");
            mentorMenu();

        }

    }

    private static void addMentor() {
        System.out.println("----------ADD MENTOR PANEL ----------------");
        Mentor mentor = Mentor.builder()
               .firstName(Input.input_STRING("First Name: "))
               .lastName(Input.input_STRING("Last Name: "))
                .username(Input.input_STRING("Username: "))
                .password(Input.input_STRING("Password: "))
                .email(Input.input_STRING("Email: "))
               .build();
        DB.mentors.add(mentor);
        System.out.println("✅Mentor secssesfully add");
        mentorMenu();

    }

    public static Mentor chooseMentor() {
        System.out.println("----------SELECT MENTOR PANEL ----------------");
        int i = 1;
        for (Mentor mentor : DB.mentors) {
            System.out.println(i++ + "." +mentor.getFirstName() + " " + mentor.getLastName());
        }
        int index = Input.input_INT("choose: ")-1;
        System.out.println("✅select MENTOR: "+ DB.mentors.get(index).getFirstName() + " " + DB.mentors.get(index).getLastName());
        return DB.mentors.get(index);
    }

    public static void mentorPanel(Mentor mentor) {
        System.out.println("---------- MY GROUP LIST ----------------");
        int n = 1;
        for (Group group : DB.groups) {
            if(group.getMentor().getUsername().equals(mentor.getUsername())){
                System.out.printf("%d. %s %n", n++, group.getName());
            }
        }
        System.out.println("0.LOG OUT");
        int index = Input.input_INT("choose: ")-1;
        if(index+1 == 0){
            Main.startPrograma();
        }
        attendanceOfStudentFromGroup(DB.groups.get(index), mentor);

    }

    private static void attendanceOfStudentFromGroup(Group group, Mentor mentor) {

        System.out.println("----------ATTENDANCE PANEL ----------------");

        System.out.println("GROUP: " + group.getName());
        String attandance = "  ";


        attandancePanel(group,attandance);
        System.out.println("""
                1.ALL STUDENT 🟢
                2.ALL STUDENT 🔴
                
                0.BACK""");
        int choose = Input.input_INT("choose: ");
        if (choose == 1) {
            attandance = "🟢";
           attandancePanel(group, attandance);
        }else if(choose == 2) {
            attandance = "🔴";
            attandancePanel(group,attandance);
        }else{
           mentorPanel(mentor);
        }

    }

    private static void attandancePanel(Group group, String attandance) {
        int n = 1;
        System.out.println("N |  STUDENT'S FULL NAME  |    LESSON-1   |    LESSON-2   |    LESSON-3   |    LESSON-4   |    LESSON-5   |    LESSON-6   |    LESSON-7   |    LESSON-8   |    LESSON-9   |    LESSON-10  |    LESSON-11  |    LESSON-12  |");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("\t\t\t\t\t   ");
        for (LocalDate lessonDate : group.getTimeTable().getLessonDates()) {
            System.out.print("   |  " + lessonDate);
        }
        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Student student : group.getStudents()) {
            student.setAttandance(attandance);
            System.out.println(n++ + " | " + checkSymbol(student.getFirstName() + " " + student.getLastName()) + "|      %s       ".formatted(student.getAttandance()).repeat(12) + "|");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    private static String checkSymbol(String fullName) {
        int length = fullName.length() - 1;
        if(length == 23){
            return fullName;
        }else if(length < 23){
            return fullName + " ".repeat(23 - length-2);
        }else{
            return fullName.substring(0, 22);
        }
    }
}
