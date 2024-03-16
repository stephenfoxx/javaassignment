import com.hjss.controllers.*;
import com.hjss.models.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    private final AppController appController;

    public App() {
        // Initialize controllers
        List<Student> students = new ArrayList<>();
        var studentController = new StudentController(students);
        studentController.createStudents();

        List<Coach> coaches = new ArrayList<>();
        var coachController = new CoachController(coaches);
        coachController.createCoaches();

        List<Lesson> lessons = new ArrayList<>();
        var lessonController = new LessonController(lessons, coaches);
        lessonController.createLessons();

        appController = new AppController(studentController, lessonController, coachController);
    }

    public void start() {
        // Start the application by showing the main menu for students
        // Keep Looping the application until the user manually exits the application.
        do appController.showMainMenu(); while (true);
    }
}
