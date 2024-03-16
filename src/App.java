import com.hjss.controllers.*;
import com.hjss.views.*;
import com.hjss.models.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    // Controllers
    private final AppController appController;

    // Database
    private final List<Student> students = new ArrayList<>();
    private final List<Coach> coaches = new ArrayList<>();

    private final List<Lesson> lessons = new ArrayList<>();

    public App () {
        // Initialize controllers
        var studentController = new StudentController(students);
        studentController.createStudents();

        var coachController = new CoachController(coaches);
        coachController.createCoaches();

        var lessonController = new LessonController(lessons, coaches);
        lessonController.createLessons();

        appController = new AppController(studentController, lessonController);
    }

    public void start() {
        // Start the application by showing the main menu for learners
        appController.showMainMenu();
    }
}
