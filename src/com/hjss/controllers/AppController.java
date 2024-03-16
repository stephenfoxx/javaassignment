package com.hjss.controllers;

import com.hjss.enums.*;
import com.hjss.exception.*;
import com.hjss.models.*;
import com.hjss.views.*;

import java.util.List;

public class AppController {
    /**
     * Views are the Menu classes
     */
    private final MainView mainView;
    private final BookLessonView bookLessonView;
    private final BookByDayView bookByDayView;
    private final TimeTableView timeTableView;
    private final BookByCoachView bookByCoachView;

    private final BookByGradeView bookByGradeView;

    /**
     * Controller classes to handle Model logic
     */
    private final StudentController studentController;
    private final LessonController lessonController;
    private final CoachController coachController;

    /**
     * Internally used data properties
     */
    private Student loggedInStudent;

    private List<Lesson> lessons;

    /**
     *
     * @param studentController Dependency injection of the student controller.
     * @param lessonController Dependency injection of the lesson controller
     */
    public AppController(StudentController studentController, LessonController lessonController, CoachController coachController) {
        // Instantiate and assign the Application menu options.
        this.mainView = new MainView();
        this.bookLessonView = new BookLessonView();
        this.bookByDayView = new BookByDayView();
        this.timeTableView = new TimeTableView();
        this.bookByCoachView = new BookByCoachView();
        this.bookByGradeView = new BookByGradeView();

        // Inject controllers into Class instance
        this.studentController = studentController;
        this.lessonController = lessonController;
        this.coachController = coachController;
    }

    // First interaction with the application is to display the application menu
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
                Student rgs = studentController.register();
                setLoggedInStudent(rgs);
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

        switch (choice) {
            case 1:
                handleBookByDay();
                break;
            case 2:
                handleBookByCoach();
                break;
            case 3:
                handleBookByGrade();
                break;
        }
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
        Day day;
        bookByDayView.displayMenu();
        int choice;
        choice = bookByDayView.getMenuChoice();

        if (choice == 0) System.exit(0);

        day = switch (choice) {
            case 1 -> Day.MONDAY;
            case 2 -> Day.WEDNESDAY;
            case 3 -> Day.FRIDAY;
            case 4 -> Day.SATURDAY;
            default -> null;
        };

        List<Lesson> lessons = lessonController.getLessons(day);

        setLessons(lessons);

        String timeTable = lessonController.getTimeTable(lessons);

        handleBookLesson(timeTable);
    }

    private void handleBookByCoach() {
        Coach coach;

        // Update coaches
        bookByCoachView.setCoaches(coachController.getCoaches());

        bookByCoachView.displayMenu();

        int choice;
        choice = bookByCoachView.getMenuChoice();

        if (choice == 0) System.exit(0);

        coach = coachController.getCoach(choice);

        List<Lesson> lessons = lessonController.getLessons(coach);

        setLessons(lessons);

        String timeTable = lessonController.getTimeTable(lessons);

        handleBookLesson(timeTable);
    }

    private void handleBookByGrade() {
        Grade grade;

        bookByGradeView.displayMenu();

        int choice;
        choice = bookByGradeView.getMenuChoice();

        if (choice == 0) System.exit(0);

        grade = switch (choice) {
            case 1 -> Grade.ONE;
            case 2 -> Grade.TWO;
            case 3 -> Grade.THREE;
            case 4 -> Grade.FOUR;
            case 5 -> Grade.FIVE;
            default -> null;
        };

        List<Lesson> lessons = lessonController.getLessons(grade);

        setLessons(lessons);

        String timeTable = lessonController.getTimeTable(lessons);

        handleBookLesson(timeTable);
    }

    private void handleBookLesson(String timeTable) {
        int choice;

        timeTableView.displayMenu(timeTable);

        for (Lesson ls : lessons) {
            timeTableView.setId(ls.getId());
        }

        choice = timeTableView.getMenuChoice();

        if (choice == 0) System.exit(0);
        if (choice == 45) handleBookSwimmingLesson();

        Lesson lessonChoice = lessonController.getLesson(choice);

        try {
            lessonController.bookLesson(lessonChoice, getLoggedInStudent());
            System.out.println("You have been booked for Lesson " + lessonChoice.getId() + " on " + lessonChoice.getDay() + " by " + lessonChoice.getTime().getValue());
        } catch (MaxLessonCapacityException | NotMatchingGradeException e) {
            System.out.println(e.getMessage());

            // recursive method call
            handleBookLesson(timeTable);
        }
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
