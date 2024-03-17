import com.hjss.controllers.*;
import com.hjss.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class of the application responsible for bootstrapping the application and initializing controllers.
 * It demonstrates dependency injection by providing instances of various controllers to the AppController.
 */
public class App {
    /**
     * The main controller of the application
     */
    private final AppController appController;

    public App() {
        // Initialize controllers
        List<Student> students = new ArrayList<>();
        var studentController = new StudentController(students);
        studentController.createStudents();                // Pre-create students while bootstrapping the application.

        List<Coach> coaches = new ArrayList<>();
        var coachController = new CoachController(coaches);
        coachController.createCoaches();                   // Pre-create coaches while bootstrapping the application.

        List<Lesson> lessons = new ArrayList<>();
        var lessonController = new LessonController(lessons);
        lessonController.createLessons(coaches);           // Pre-create lessons while bootstrapping the application.

        List<Booking> bookings = new ArrayList<>();
        var bookingController = new BookingController(bookings);

        List<Review> reviews = new ArrayList<>();
        var reviewController = new ReviewController(reviews);

        // Inject into App controller
        appController = new AppController(studentController, lessonController, coachController, bookingController, reviewController);
    }

    /**
     * Starts the application by displaying the main menu for students.
     * Keeps looping the application until the user manually exits.
     */
    public void start() {
        do appController.showMainMenu(); while (true);
    }
}
