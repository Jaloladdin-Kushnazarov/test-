package org.example.servises;

import org.example.entity.TimeTable;
import org.example.utiils.Input;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TimeTableServices {

    public static List<LocalDate> timeTableOfGroup(LocalDate startDate) {
        List<LocalDate> localDates = new ArrayList<>(12);


        LocalDate currentDate = startDate;


        boolean isEvenDay = isEvenDayOfWeek(currentDate.getDayOfWeek());


        while (localDates.size() < 12) {

            if (currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                if ((isEvenDay && isEvenDayOfWeek(currentDate.getDayOfWeek())) ||
                        (!isEvenDay && isOddDayOfWeek(currentDate.getDayOfWeek()))) {
                    localDates.add(currentDate);
                }
            }

            currentDate = currentDate.plusDays(1);
        }

        return localDates;
    }


    private static boolean isEvenDayOfWeek(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.TUESDAY ||
                dayOfWeek == DayOfWeek.THURSDAY ||
                dayOfWeek == DayOfWeek.SATURDAY;
    }


    private static boolean isOddDayOfWeek(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.MONDAY ||
                dayOfWeek == DayOfWeek.WEDNESDAY ||
                dayOfWeek == DayOfWeek.FRIDAY;
    }

    public static TimeTable selectTime() {
        System.out.println("----------SELECT DATE PANEL ----------------");
        System.out.println("""
                When do you want the group lesson to start?
                1. Today
                2. Enter start date         
                """);
        int choose = Input.input_INT("choose: ");

        if (choose == 1) {
            return new TimeTable(timeTableOfGroup(LocalDate.now()));
        } else {
            LocalDate startDate = inputStartDate();
            return new TimeTable(timeTableOfGroup(startDate));
        }
    }

    private static LocalDate inputStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (true) {
            String dateStr = Input.input_STRING("Enter the start date (yyyy-MM-dd): ");
            try {
                LocalDate date = LocalDate.parse(dateStr, formatter);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }
    }
}