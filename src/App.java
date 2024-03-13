import com.hjss.controllers.*;
import com.hjss.views.LearnerCliView;

public class App {
    private LearnerController learnerController;
    private CoachController coachController;
    private LessonController lessonController;
    public App () {
        LearnerCliView learnerCliView = new LearnerCliView();

        // Initialize controllers
        learnerController = new LearnerController(learnerCliView);
        coachController = new CoachController();
        lessonController = new LessonController();
    }

    public void start() {
        // Start the application by showing the main menu for learners
        learnerController.showMainMenu();
    }
}
