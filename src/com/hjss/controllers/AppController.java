package com.hjss.controllers;

import com.hjss.exception.InvalidAgeException;
import com.hjss.models.Student;
import com.hjss.views.*;

public class AppController {
    private MainView mainView;
    private StudentController studentController;
    private BookLessonView bookLessonView;
    private Student loggedInStudent;

    public AppController(MainView mainView, StudentController studentController) {
        this.mainView = mainView;
        this.studentController = studentController;
        this.bookLessonView = new BookLessonView();
    }

    public void showMainMenu() {
        mainView.displayMenu();
        int choice = mainView.getMenuChoice();

        // Based on the choice, perform appropriate actions
        switch (choice) {
            case 1:
                // Login
                handleLogInUser();

                // Handle booking a lesson logic
                handleBookSwimmingLesson();
                break;
            // Add more cases for other menu options
            case 2:
                // Login
                handleLogInUser();

                System.out.println("Change/Cancel a booking");
                // Handle registration logic
                break;
            case 3:
                // Login
                handleLogInUser();

                System.out.println("Attend a swimming lesson");
                // Handle registration logic
                break;
            case 4:
                // Login
                handleLogInUser();

                System.out.println("Monthly learner report");
                // Handle registration logic
                break;
            case 5:
                // Login
                handleLogInUser();

                System.out.println("Monthly coach report");
                // Handle registration logic
                break;
            case 6:
                // Handle registration logic
                handleRegisterStudent();
                showMainMenu();
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

    private void handleBookSwimmingLesson() {

        // Now we split into three branches
        // 1. By day
        // 2. By Coach
        // 3. By Grade

        // Print the menu first
        bookLessonView.displayMenu();
        int choice = bookLessonView.getMenuChoice();


    }

    private void handleLogInUser() {
        // Ensure we Know who is performing this action.
        Student currentUser = studentController.login();

        // Current user will be null If the user requests to register a student from the List
        if (currentUser == null) {
            // Run the handle register student method
            handleRegisterStudent();
        } else {
            setLoggedInStudent(currentUser);
        }
    }

    public Student getLoggedInStudent() {
        return loggedInStudent;
    }

    public void setLoggedInStudent(Student loggedInStudent) {
        this.loggedInStudent = loggedInStudent;
    }

    private void handleBookByDay() {

    }
}
