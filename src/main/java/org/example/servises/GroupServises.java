package org.example.servises;

import org.example.db.DB;
import org.example.entity.Course;
import org.example.entity.Group;
import org.example.entity.Student;
import org.example.utiils.Input;

import java.util.ArrayList;
import java.util.List;

public class GroupServises {
    public static void addGroup() {
        System.out.println("----------ADD GROUP PANEL ----------------");
        Group group = Group.builder()
                .name(Input.input_STRING("Enter group name: "))
                .timeTable(TimeTableServices.selectTime())
                .mentor(MentorServises.chooseMentor())
                .students(new ArrayList<>())
                .build();


        int temp = Input.input_INT("""
                Do you want Students to add the Group?
                                
                1. YES
                2. NO
                choose: """);

        if (temp == 1) {

            List<Student> tempStudents = StudentServises.chooseStudent(group.getStudents());
            group.getStudents().addAll(tempStudents);
            System.out.println("✅Students added successfully.");
            DB.groups.add(group);
            System.out.println("✅ Successfully added group with students.");
            groupMenu();
        } else {

            DB.groups.add(group);
            System.out.println("✅ Successfully added groups");


        }
    }

    static void groupMenu() {
        System.out.println("""
                --------------------------------
                1.ADD GROUP
                2.INFO GROUP
                
                0.BACK
                --------------------------------""");
        switch (Input.input_INT("choose: ")) {
            case 1: addGroup();
            case 2: infoGroup();
            case 0: AdminServises.adminMenu();
        }

    }

    private static void infoGroup() {
        System.out.println("----------INFO GROUP PANEL ----------------");
        for (int i = 0; i < DB.groups.size(); i++) {
            System.out.println(i + 1 + ". " + DB.groups.get(i).getName());
        }
        System.out.println("\n0.BACK");
        int index = Input.input_INT("choose: ");
        if (index == 0) {
            groupMenu();
        }
        Group group = DB.groups.get(index - 1);
        System.out.println("Group Name: " + group.getName());
        System.out.println("Group Mentor: " + group.getMentor().getFirstName() + " " + group.getMentor().getLastName());
        System.out.println("Group TimeTable: " + group.getTimeTable());
        System.out.println("Group Students: " );
        int n = 1;
        for (Student student : group.getStudents()) {
            System.out.println("\t" +n++ + "." + student.getFirstName() + " " + student.getLastName());
        }
    }


    public static List<Group> chooseGroup(List<Group> courseGroups) {

        List<Group> checkedGroup = new ArrayList<>(DB.groups);
        checkedGroup.removeAll(courseGroups);

        if (checkedGroup.isEmpty()) {
            System.out.println("❌You have not groups to add.");
            return new ArrayList<>();
        }

        List<Group> selectedGroups = new ArrayList<>();

        while (true) {
            int number = 1;
            if(checkedGroup.isEmpty()){
                System.out.println("❌You have not groups to add.");
                int i = Input.input_INT("""
                        0.Back
                        choose: """);
                if(i >= 0){
                    break;
                }
            }
            for (Group group : checkedGroup) {
                System.out.println(number++ + ". " + group.getName());
            }

            int index = Input.input_INT("Choose group: ") - 1;
            if (index >= 0 && index < checkedGroup.size()) {
                Group selectedGroup = checkedGroup.get(index);
                selectedGroups.add(selectedGroup);
                checkedGroup.remove(selectedGroup);

                System.out.println("✅Group '" + selectedGroup.getName() + "' added.");

                int choosePosition = Input.input_INT("""
                    🔴 Do you want to add another group?
                                        
                    1. YES
                    2. NO
                    choose: """);
                if (choosePosition != 1) {
                    break;
                }
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }

        return selectedGroups;
    }
}
