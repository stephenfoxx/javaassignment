import com.hjss.controllers.*;
import com.hjss.views.StudentCliView;

public class App {
    private StudentController studentController;
    private CoachController coachController;
    private LessonController lessonController;
    public App () {
        StudentCliView studentCliView = new StudentCliView();

        // Initialize controllers
        studentController = new StudentController(studentCliView);
        coachController = new CoachController();
        lessonController = new LessonController();
    }

    public void start() {
        // Start the application by showing the main menu for learners
        studentController.showMainMenu();
    }
}
