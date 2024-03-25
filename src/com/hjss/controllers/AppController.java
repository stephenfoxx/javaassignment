package com.hjss.controllers;

import com.hjss.enums.*;
import com.hjss.exception.*;
import com.hjss.models.*;
import com.hjss.views.*;

import java.util.List;

public class AppController {
    /**
     * Views representing different menu options in the application.
     */
    private final MainView mainView;                 // Main menu view
    private final BookLessonView bookLessonView;     // View for booking lessons
    private final BookByDayView bookByDayView;       // View for booking lessons by day options
    private final BookByCoachView bookByCoachView;   // View for booking lessons by coach
    private final BookByGradeView bookByGradeView;   // View for booking lessons by grade
    private final TimeTableView timeTableView;       // View for displaying timetable
    private final ChangeCancelView changeCancelView; // View for changing or cancelling bookings

    /**
     * Controller classes to handle model logic.
     */
    private final StudentController studentController;    // Controller for student-related operations
    private final LessonController lessonController;      // Controller for lesson-related operations
    private final CoachController coachController;        // Controller for coach-related operations
    private final BookingController bookingController;    // Controller for booking-related operations
    private final ReviewController reviewController;      // Controller for review-related operations


    /**
     * Internally used data properties.
     */
    private Student loggedInStudent;                     // Currently logged-in student
    private List<Lesson> lessons;                        // List of lessons available in the system

    /**
     * @param studentController Dependency injection of the student controller.
     * @param lessonController  Dependency injection of the lesson controller.
     * @param coachController   Dependency injection of the coach controller.
     * @param bookingController Dependency injection of the booking controller.
     * @param reviewController  Dependency injection of the review controller.
     */
    public AppController(
            StudentController studentController,
            LessonController lessonController,
            CoachController coachController,
            BookingController bookingController,
            ReviewController reviewController
    ) {

        // Instantiate and assign the Application Views.
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

    /**
     * Displays the main menu of the application and handles user interaction.
     * Users can choose various options such as login, booking lessons, changing/cancelling bookings,
     * attending a lesson, viewing learner or coach reports,
     * registering as a student, or exiting the application.
     */
    public void showMainMenu() {
        mainView.displayMenu();
        int choice = mainView.getMenuChoice();

        // Based on the choice, perform appropriate actions
        switch (choice) {
            case 1:
                // Login and book a swimming lesson
                handleLogInUser();
                handleBookSwimmingLesson();
                break;
            case 2:
                // Login and handle change/cancel booking
                handleLogInUser();
                handleChangeOrCancelBooking();
                break;
            case 3:
                // Login and handle attending a lesson
                handleLogInUser();
                handleAttendALesson();
                break;
            case 4:
                // View all learners reports
                handleShowLearnerReport();
                break;
            case 5:
                // View all coaches reports
                handleShowCoachReport();
                break;
            case 6:
                // Register as a student
                handleRegisterStudent();
                break;
            default:
                // Exit App
                System.exit(0);
                break;
        }
    }

    /**
     * Handles the process of logging in a user.
     * Retrieves the current user by invoking the login method from the StudentController.
     * If the login method returns a null,
     * the user has opted to register a new student then it calls the handleRegisterStudent method.
     * Else, it stores the current user in memory.
     */
    private void handleLogInUser() {
        // Ensure we Know who is performing this action.
        Student currentUser = studentController.login();

        // The Current user will be null If the user requests to register a student from the List
        if (currentUser == null) {
            // Run the handle register student method
            handleRegisterStudent();
        } else {
            setLoggedInStudent(currentUser);
        }
    }

    /**
     * Handles the process of registering a learner into the application.
     * Invokes the register method in the student controller to perform the registration.
     * Once registration is successful, sets the registered user as the logged-in user in memory.
     */
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

    /**
     * Handles the process of booking a swimming lesson.
     * Provides options to book by day, coach, or grade.
     * Displays the appropriate menu and retrieves user input to select the booking method.
     * Once a booking method is chosen, delegates to the corresponding handler method.
     */
    private void handleBookSwimmingLesson() {

        // Display the booking menu and get user choice.
        bookLessonView.displayMenu();
        int choice = bookLessonView.getMenuChoice();

        // Based on the user's choice, handle the booking process.
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

    /**
     * Handles the process of booking a swimming lesson by day.
     * Displays the menu to choose the day (Monday, Wednesday, Friday and Saturday),
     * retrieves user input, and fetches available lessons for the selected day.
     * Sets the retrieved lessons and returns the timetable for the chosen day.
     *
     * @return The timetable for the chosen day over a period of 4 weeks.
     */
    private String handleBookByDay() {
        // Declare variables
        Day day;
        int choice;

        // Display the menu to choose day.
        bookByDayView.displayMenu();
        choice = bookByDayView.getMenuChoice();

        if (choice == 0) System.exit(0);

        // Handle user choice
        day = switch (choice) {
            case 1 -> Day.MONDAY;
            case 2 -> Day.WEDNESDAY;
            case 3 -> Day.FRIDAY;
            case 4 -> Day.SATURDAY;
            default -> null;
        };

        // Retrieve lessons for the chosen day
        List<Lesson> lessons = lessonController.getLessons(day);

        // Set the retrieved lessons
        setLessons(lessons);

        // Return the timetable for the chosen day
        return lessonController.getTimeTable(lessons);
    }

    /**
     * Handles the process of booking a swimming lesson by coach.
     * Displays the menu to choose the coach, retrieves user input,
     * and fetches available lessons for the selected coach.
     * Sets the retrieved lessons and returns the timetable for the chosen coach.
     *
     * @return The timetable for the chosen coach over a 4-week period.
     */

    private String handleBookByCoach() {
        // Declare variables
        Coach coach;
        int choice;

        // Set the coaches dependency on coaches before displaying the menu.
        bookByCoachView.setCoaches(coachController.getCoaches());

        // Display coaches menu
        bookByCoachView.displayMenu();

        choice = bookByCoachView.getMenuChoice();

        // Handle user choice
        if (choice == 0) System.exit(0);
        coach = coachController.getCoach(choice);

        // Retrieve lessons for the chosen coach
        List<Lesson> lessons = lessonController.getLessons(coach);

        // Set the retrieved lessons
        setLessons(lessons);

        // Return the timetable for the chosen coach
        return lessonController.getTimeTable(lessons);
    }

    /**
     * Handles the process of booking a swimming lesson by grade.
     * Displays the menu to choose the grade, retrieves user input,
     * and fetches available lessons for the selected grade.
     * Sets the retrieved lessons and returns the timetable for the chosen grade.
     *
     * @return The timetable for the chosen grade over a 4-week period.
     */
    private String handleBookByGrade() {
        Grade grade;
        int choice;

        // Display grade menu.
        bookByGradeView.displayMenu();

        // Get user choice
        choice = bookByGradeView.getMenuChoice();

        // Handle User choice
        if (choice == 0) System.exit(0);

        grade = switch (choice) {
            case 1 -> Grade.ONE;
            case 2 -> Grade.TWO;
            case 3 -> Grade.THREE;
            case 4 -> Grade.FOUR;
            case 5 -> Grade.FIVE;
            default -> null;
        };

        // Retrieve lessons for the chosen grade
        List<Lesson> lessons = lessonController.getLessons(grade);

        // Set the retrieved lessons
        setLessons(lessons);

        // Return the timetable for the chosen grade
        return lessonController.getTimeTable(lessons);
    }

    /**
     * Handles the process of booking a swimming lesson.
     * Displays the timetable, retrieves user input to select a lesson, and creates a booking for the selected lesson.
     * It Prints the booking information if the booking is successful or handles exceptions if encountered.
     *
     * @param timeTable The timetable for the selected booking method over a 4-week period.
     */
    private void handleBookLesson(String timeTable) {
        // Declare Variables
        int choice;

        // Display the timetable and get user choice
        timeTableView.displayMenu(timeTable);

        // Set dependency for timeTable view
        for (Lesson ls : lessons) {
            timeTableView.setId(ls.getId());
        }

        // Get User Choice
        choice = timeTableView.getMenuChoice();

        // Handle Choice
        if (choice == 0) System.exit(0);

        // Retrieve the lesson of choice.
        Lesson lessonChoice = lessonController.getLesson(choice);

        try {
            // Attempt to create a booking for the selected lesson
            Booking booking = bookingController.createBooking(getLoggedInStudent(), lessonChoice);

            // Print success message
            System.out.println();
            System.out.println("\u001B[32mSuccess: Your booking was completed successfully\u001B[0m");
            System.out.println();

            // Print booking information
            System.out.println("Here is your booking information: ");
            System.out.println(booking.toString());
        } catch (MaxLessonCapacityException | NotMatchingGradeException | DuplicateBookingException e) {
            // Print error message for encountered exceptions
            System.out.println();
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");

            // Retry the booking process recursively
            handleBookLesson(timeTable);
        }
    }

    /**
     * Handles the process of changing or cancelling a booking.
     * Retrieves the bookings of the logged-in student and displays them.
     * Allows the user to select a booking to change or cancel.
     * Displays the options to change or cancel the selected booking and delegates to the corresponding handler method.
     */
    private void handleChangeOrCancelBooking() {
        // Declare variables
        int choice;

        // Retrieve bookings of the logged-in student
        List<Booking> bookings = bookingController.getBookings(getLoggedInStudent());

        // Check if the student has any bookings
        if (bookings.isEmpty()) {
            // Print message if the student has no bookings
            System.out.println();
            System.out.println("\u001B[31mYou do not have any bookings\u001B[0m");
            return;
        }

        // Display the student's bookings and get user choice
        choice = bookingController.showBookings(bookings);

        // Get the selected booking
        Booking booking = bookingController.getBooking(choice);

        // Display the menu for changing or cancelling booking
        changeCancelView.displayMenu();
        choice = changeCancelView.getMenuChoice();

        // Handle user choice
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

    /**
     * Handles the process of changing a booking.
     * Displays menu options for changing the booking by day, coach, or grade.
     * Delegates to the corresponding handler method based on the user's choice.
     *
     * @param booking The booking to be changed.
     */
    private void handleChangeBooking(Booking booking) {
        // Display menu options for changing booking
        bookLessonView.displayMenu();
        int choice = bookLessonView.getMenuChoice();

        // Handle user choice
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

    /**
     * Handles the cancellation of a booking.
     *
     * @param booking The booking to be cancelled.
     */
    private void handleCancelBooking(Booking booking) {

        try {
            // Attempt to cancel the booking
            bookingController.cancelBooking(booking, loggedInStudent);

            // Print success message
            System.out.println();
            System.out.println("\u001B[32mSuccess: Your booking was successfully cancelled\u001B[0m");

            // Print details of the cancelled booking
            System.out.println();
            System.out.println(booking);
        } catch (ForbiddenException | BookingAttendedException e) {
            // If an exception occurs, print an error message and prompt the user to select a booking again
            System.out.println();
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            handleChangeOrCancelBooking();
        }
    }

    /**
     * Handles the process of changing a specific booking.
     * Displays the timetable for available lessons based on the provided timetable string.
     * Allows the user to select a lesson to change the booking.
     * If the selected lesson is valid, update the booking accordingly.
     * If an exception occurs during the process, it displays an error message and allows the user to retry.
     *
     * @param timeTable The string representation of the timetable for available lessons.
     * @param booking   The booking to be changed.
     */
    private void handleChangeLesson(String timeTable, Booking booking) {
        int choice;

        // Display time table for available lessons
        timeTableView.displayMenu(timeTable);

        // Set lesson IDs for display
        for (Lesson ls : lessons) {
            timeTableView.setId(ls.getId());
        }

        // Get user choice for lessons
        choice = timeTableView.getMenuChoice();

        if (choice == 0) System.exit(0);

        // Retrieve the selected lesson
        Lesson lessonChoice = lessonController.getLesson(choice);

        try {
            // Attempt to change the booking with the selected lesson
            Booking updated = bookingController.changeBooking(booking, lessonChoice, loggedInStudent);

            // Print Success Message
            System.out.println();
            System.out.println("\u001B[32mSuccess: Booking with ID" + updated.getId() + " updated" + "\u001B[0m");
            System.out.println();
        } catch (MaxLessonCapacityException | NotMatchingGradeException | DuplicateBookingException |
                 ForbiddenException | BookingAttendedException e) {

            // Handle exceptions by displaying an error message
            System.out.println();
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");

            // Attempt to retry recursively.
            handleChangeLesson(timeTable, booking);
        }
    }

    /**
     * Handles the process of attending a lesson.
     * Displays the list of bookings for the logged-in student.
     * Allows the user to select a booking to attend.
     * Upon attending a lesson, creates a review for the attended lesson and displays the review information.
     * If the student has no bookings, it displays a message indicating that there are no bookings.
     */
    private void handleAttendALesson() {
        int choice;

        // Get the logged-in student Bookings
        List<Booking> bookings = bookingController.getBookings(getLoggedInStudent());

        // Check if the bookings are empty
        if (bookings.isEmpty()) {
            // Print No booking message.
            System.out.println(" ");
            System.out.println("\u001B[31mYou do not have any bookings\u001B[0m");
            System.out.println(" ");
            return;
        }

        // Display bookings and get user choice
        choice = bookingController.showBookings(bookings);

        // Retrieve the selected booking
        Booking booking = bookingController.getBooking(choice);

        // Display booking information
        System.out.println();
        System.out.println("You are about to attend a booking with this information: ");
        System.out.println(booking);

        try {
            // Attend the selected lesson
            bookingController.attendLesson(booking);
        } catch (ForbiddenException e) {
            System.out.println();
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            return;
        }

        // Display a success message for attending the lesson
        System.out.println("\u001B[32mSuccess: Your have successfully attended your booking!\u001B[0m");

        // Display updated booking information
        System.out.println(booking);

        // Create a review for the attended lesson by prompting the learner to add review.
        Review review = reviewController.create(booking);

        // Display a success message for creating the review
        System.out.println();
        System.out.println("\u001B[32mSuccess: Your have successfully entered your review!\u001B[0m");
        System.out.println();

        // Display review information
        System.out.println(review);

    }

    /**
     * Generates and displays a report for the Hatfield Junior Swimming School for a 4-week period.
     * The report includes statistics for each student, such as the total number of bookings, attended lessons,
     * and cancelled lessons.
     * Additionally, it lists the lessons booked by each student.
     */
    private void handleShowLearnerReport() {
        System.out.println();
        System.out.println("Report For Hatfield Junior Swimming School For The Month");

        // Get all students
        List<Student> students = studentController.getStudents();

        for (Student st : students) {
            // Get student bookings, cancelled bookings, and attended bookings
            List<Booking> bookings = bookingController.getBookings(st);
            List<Booking> cancelledBookings = bookingController.getCancelledBookings(st);
            List<Booking> attendedBookings = bookingController.getAttendedBookings(st);

            // Print student information and statistics
            System.out.println(st + "\nTotal Booking: " + bookings.size() + "\nTotal Attended: " + attendedBookings.size() + "\nTotal Cancelled: " + cancelledBookings.size());

            // Print lessons booked by the student
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

    /**
     * Generates and displays a monthly report for each coach.
     * The report includes the coach's name and their average rating based on learner reviews.
     */
    private void handleShowCoachReport() {
        System.out.println();
        System.out.println("Monthly coach report");

        // Get all coaches
        List<Coach> coaches = coachController.getCoaches();

        // Iterate over each coach
        for (Coach ch : coaches) {
            // Get reviews for each coach
            List<Review> reviews = reviewController.getReviews(ch);

            float avgRating = 0;

            // Calculate average rating
            if (!reviews.isEmpty()) {
                int sum = 0;
                for (Review rv : reviews) {
                    sum += rv.getRating().getValue();
                }

                avgRating = (float) sum / reviews.size();
            }

            // Print coach information and average rating
            System.out.println("Name: " + ch.getName() + "\nAverage Rating: " + avgRating);
        }
    }

    /**
     * Sets the list of lessons.
     *
     * @param lessons The list of lessons to be set.
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    /**
     * Retrieves the logged-in student.
     *
     * @return The logged-in student.
     */
    public Student getLoggedInStudent() {
        return loggedInStudent;
    }

    /**
     * Sets the logged-in student.
     *
     * @param loggedInStudent The student to be set as logged in.
     */
    private void setLoggedInStudent(Student loggedInStudent) {
        this.loggedInStudent = loggedInStudent;
    }
}
