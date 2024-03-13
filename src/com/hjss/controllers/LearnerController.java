package com.hjss.controllers;

import com.hjss.views.LearnerCliView;

public class LearnerController {
    private LearnerCliView learnerCliView;

    public LearnerController(LearnerCliView learnerCliView) {
        this.learnerCliView = learnerCliView;
    }

    public void showMainMenu() {
        learnerCliView.displayMenu();
        int choice = learnerCliView.getMenuChoice();

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
