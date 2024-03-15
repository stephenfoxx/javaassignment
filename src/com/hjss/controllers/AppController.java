package com.hjss.controllers;

import com.hjss.exception.InvalidAgeException;
import com.hjss.models.Student;
import com.hjss.views.MainView;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    private MainView mainView;
    private StudentController studentController;



    public AppController(MainView mainView, StudentController studentController) {
        this.mainView = mainView;
        this.studentController = studentController;
    }

    public void showMainMenu() {
        mainView.displayMenu();
        int choice = mainView.getMenuChoice();

        // Based on the choice, perform appropriate actions
        switch (choice) {
            case 1:
                // Handle booking a lesson logic
                System.out.println("Book a swimming lesson");
                break;
            // Add more cases for other menu options
            case 2:
                System.out.println("Change/Cancel a booking");
                // Handle registration logic
                break;
            case 3:
                System.out.println("Attend a swimming lesson");
                // Handle registration logic
                break;
            case 4:
                System.out.println("Monthly learner report");
                // Handle registration logic
                break;
            case 5:
                System.out.println("Monthly coach report");
                // Handle registration logic
                break;
            case 6:
                // Handle registration logic
                handleRegisterStudent();
                break;
            case 0:
                // Exit App
                System.exit(0);
                break;
            default:
                // Handle invalid choice
                break;
        }
    }

    private void handleRegisterStudent() {
        System.out.println("Register a new student");
        boolean registrationSuccessful = false;

        while (!registrationSuccessful) {
            try {
                studentController.register();
                registrationSuccessful = true; // If registration succeeds, exit the loop
            } catch (InvalidAgeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
