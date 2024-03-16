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
        var mainView = new MainView();

        // Initialize controllers
        var studentController = new StudentController(students);
        studentController.createStudents();

        var coachController = new CoachController(coaches);
        coachController.createCoaches();

        var lessonController = new LessonController(lessons, coaches);
        lessonController.createLessons();

        appController = new AppController(mainView, studentController);
    }

    public void start() {
        System.out.println(lessons.size());
        for (Lesson ls: lessons) {
            System.out.println(ls.getDay() + " " + ls.getTime() + " " + ls.getCoach().getName() + " " + ls.getGrade());
        }
        // Start the application by showing the main menu for learners
        appController.showMainMenu();
    }
}
