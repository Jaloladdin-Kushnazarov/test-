package org.example;

import org.example.db.DB;
import org.example.servises.AuthServises;
import org.example.utiils.Input;

import static org.example.servises.AuthServises.login;


public class Main {
    static {
        DB.generateData();
    }

    public static void main(String[] args) {
        startPrograma();

    }

    public static void startPrograma() {
        System.out.println("-------------LOGIN PANEL-------------");
        Object user = login(Input.input_STRING("Enter your username:"), Input.input_STRING("Enter your password"));
        System.out.println("-------------------------------------");
        AuthServises.CheckUser(user);
    }
}