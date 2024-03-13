package com.hjss.controllers;

import com.hjss.views.StudentCliView;

public class StudentController {
    private StudentCliView studentCliView;

    public StudentController(StudentCliView studentCliView) {
        this.studentCliView = studentCliView;
    }

    public void showMainMenu() {
        studentCliView.displayMenu();
        int choice = studentCliView.getMenuChoice();

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
                System.out.println("Register a new learner");
                // Handle registration logic
                break;
            case 0:
                System.exit(0);
                // Handle registration logic
                break;
            default:
                // Handle invalid choice
                break;
        }
    }
}
