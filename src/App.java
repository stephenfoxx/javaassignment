import com.hjss.controllers.*;
import com.hjss.views.*;
import com.hjss.models.*;

import java.util.ArrayList;
import java.util.List;

public class App {
    // Controllers
    private final AppController appController;
    private StudentController studentController;
//    private CoachController coachController;
//    private LessonController lessonController;

    // Database
    private final List<Student> students = new ArrayList<>();

    public App () {
        var mainView = new MainView();

        // Initialize controllers
        studentController = new StudentController(students);
//        coachController = new CoachController();
//        lessonController = new LessonController();

        appController = new AppController(mainView, studentController);
    }

    public void start() {
        // Start the application by showing the main menu for learners
        appController.showMainMenu();

        for(Student student: students) {
            System.out.println(student.getAsString());
        }
    }
}
