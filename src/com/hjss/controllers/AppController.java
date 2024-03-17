package com.hjss.controllers;

import com.hjss.enums.*;
import com.hjss.exception.*;
import com.hjss.models.*;
import com.hjss.views.*;

import java.util.ArrayList;
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
    private final ChangeCancelView changeCancelView;

    /**
     * Controller classes to handle Model logic
     */
    private final StudentController studentController;
    private final LessonController lessonController;
    private final CoachController coachController;
    private final BookingController bookingController;
    private final ReviewController reviewController;

    /**
     * Internally used data properties
     */
    private Student loggedInStudent;

    private List<Lesson> lessons;

    /**
     * @param studentController Dependency injection of the student controller.
     * @param lessonController  Dependency injection of the lesson controller
     */
    public AppController(StudentController studentController, LessonController lessonController, CoachController coachController, BookingController bookingController, ReviewController reviewController) {
        // Instantiate and assign the Application menu options.
        this.mainView = new MainView();
        this.bookLessonView = new BookLessonView();
        this.bookByDayView = new BookByDayView();
        this.timeTableView = new TimeTableView();
        this.bookByCoachView = new BookByCoachView();
        this.bookByGradeView = new BookByGradeView();
        this.changeCancelView = new ChangeCancelView();

        // Inject controllers into Class instance
        this.studentController = studentController;
        this.lessonController = lessonController;
        this.coachController = coachController;
        this.bookingController = bookingController;
        this.reviewController = reviewController;
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

                // Handle Change/Cancel logic
                handleChangeOrCancelBooking();
                break;
            case 3:
                // Login
                handleLogInUser();

                // Handle Attend A Lesson
                handleAttendALesson();
                break;
            case 4:
                handleShowLearnerReport();
                break;
            case 5:
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
        System.out.println(" ");
        System.out.println("Register a new student");

        boolean registrationSuccessful = false;

        while (!registrationSuccessful) {
            try {
                Student rgs = studentController.register();
                setLoggedInStudent(rgs);
                registrationSuccessful = true; // If registration succeeds, exit the loop
            } catch (InvalidAgeException e) {
                System.out.println(" ");
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
                System.out.println(" ");
            }
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
                handleBookLesson(handleBookByDay());
                break;
            case 2:
                handleBookLesson(handleBookByCoach());
                break;
            case 3:
                handleBookLesson(handleBookByGrade());
                break;
        }
    }

    private String handleBookByDay() {
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

        return lessonController.getTimeTable(lessons);
    }

    private String handleBookByCoach() {
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

        return lessonController.getTimeTable(lessons);
    }

    private String handleBookByGrade() {
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

        return lessonController.getTimeTable(lessons);
    }

    private void handleBookLesson(String timeTable) {
        int choice;

        timeTableView.displayMenu(timeTable);

        for (Lesson ls : lessons) {
            timeTableView.setId(ls.getId());
        }

        choice = timeTableView.getMenuChoice();

        if (choice == 0) System.exit(0);

        Lesson lessonChoice = lessonController.getLesson(choice);

        try {
            Booking booking = bookingController.createBooking(getLoggedInStudent(), lessonChoice);
            System.out.println();
            System.out.println("\u001B[32mSuccess: Your booking was completed successfully\u001B[0m");
            System.out.println();
            System.out.println("Here is your booking information: ");
            System.out.println(booking.toString());
        } catch (MaxLessonCapacityException | NotMatchingGradeException | DuplicateBookingException e) {
            System.out.println();
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");

            // recursive method call
            handleBookLesson(timeTable);
        }
    }

    private void handleChangeOrCancelBooking() {
        int choice = 0;
        // Get student Bookings
        List<Booking> bookings = bookingController.getBookings(getLoggedInStudent());

        if (bookings.isEmpty()) {
            System.out.println();
            System.out.println("\u001B[31mYou do not have any bookings\u001B[0m");
            return;
        }

        // Show Booking
        choice = bookingController.showBookings(bookings);

        Booking booking = bookingController.getBooking(choice);

        changeCancelView.displayMenu();
        choice = changeCancelView.getMenuChoice();

        switch (choice) {
            case 1:
                handleChangeBooking(booking);
                break;
            case 2:
                handleCancelBooking(booking);
                break;
            default:
                System.exit(0);
        }

    }

    private void handleChangeBooking(Booking booking) {
        // Now we split into three branches
        // 1. By day
        // 2. By Coach
        // 3. By Grade

        // Print the menu first
        bookLessonView.displayMenu();
        int choice = bookLessonView.getMenuChoice();

        switch (choice) {
            case 1:
                handleChangeLesson(handleBookByDay(), booking);
                break;
            case 2:
                handleChangeLesson(handleBookByCoach(), booking);
                break;
            case 3:
                handleChangeLesson(handleBookByGrade(), booking);
                break;
        }
    }

    private void handleCancelBooking(Booking booking) {

        try {
            bookingController.cancelBooking(booking, loggedInStudent);
            System.out.println();
            System.out.println("\u001B[32mSuccess: Your booking was successfully cancelled\u001B[0m");
            System.out.println();
            System.out.println(booking);
        } catch (ForbiddenException | BookingAttendedException e) {
            System.out.println();
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            handleChangeOrCancelBooking();
        }
    }

    private void handleChangeLesson(String timeTable, Booking booking) {
        int choice;

        timeTableView.displayMenu(timeTable);

        for (Lesson ls : lessons) {
            timeTableView.setId(ls.getId());
        }

        choice = timeTableView.getMenuChoice();

        if (choice == 0) System.exit(0);

        Lesson lessonChoice = lessonController.getLesson(choice);

        try {
            Booking updated = bookingController.changeBooking(booking, lessonChoice, loggedInStudent);
            System.out.println();
            System.out.println("\u001B[32mSuccess: Booking with ID" + updated.getId() + " updated" + "\u001B[0m");
            System.out.println();
        } catch (MaxLessonCapacityException | NotMatchingGradeException | DuplicateBookingException |
                 ForbiddenException | BookingAttendedException e) {
            System.out.println();
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            handleChangeLesson(timeTable, booking);
        }
    }

    private void handleAttendALesson() {
        int choice = 0;
        // Get student Bookings
        List<Booking> bookings = bookingController.getBookings(getLoggedInStudent());

        if (bookings.isEmpty()) {
            System.out.println(" ");
            System.out.println("\u001B[31mYou do not have any bookings\u001B[0m");
            System.out.println(" ");
            return;
        }

        // Show Booking
        choice = bookingController.showBookings(bookings);

        Booking booking = bookingController.getBooking(choice);

        System.out.println();
        System.out.println("You are about to attend a booking with this information: ");
        System.out.println(booking);

        bookingController.attendLesson(booking);

        System.out.println("\u001B[32mSuccess: Your have successfully attended your booking!\u001B[0m");

        System.out.println(booking);

        Review review = reviewController.create(booking);

        System.out.println();
        System.out.println("\u001B[32mSuccess: Your have successfully entered your review!\u001B[0m");
        System.out.println();

        System.out.println(review);

    }

    private void handleShowLearnerReport() {
        // Lessons
        // no of booked
        // no of cancelled
        // no of attended
        System.out.println();
        System.out.println("Report For Hatfield Junior Swimming School For The Month");

        List<Student> students = studentController.getStudents();

        for (Student st : students) {
            // Get student bookings
            List<Booking> bookings = bookingController.getBookings(st);
            List<Booking> cancelledBookings = bookingController.getCancelledBookings(st);
            List<Booking> attendedBookings = bookingController.getAttendedBookings(st);

            System.out.println(st + "\nTotal Booking: " + bookings.size() + "\nTotal Attended: " + attendedBookings.size() + "\nTotal Cancelled: " + cancelledBookings.size());

            System.out.println("Lessons booked by " + st.getName() + ":");
            if (bookings.isEmpty()) {
                System.out.println();
                System.out.println("\u001B[31m" + st.getName() + " has no lesson history\u001B[0m");
                System.out.println();
            } else {
                // Lessons
                for (Booking bk : bookings) {
                    System.out.println(bk.getLesson());
                    System.out.println();
                }
            }
        }
    }

    private void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    private Student getLoggedInStudent() {
        return loggedInStudent;
    }

    private void setLoggedInStudent(Student loggedInStudent) {
        this.loggedInStudent = loggedInStudent;
    }
}
