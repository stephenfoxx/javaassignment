import com.hjss.controllers.*;

public class App {
    private LearnerController learnerController;
    private CoachController coachController;
    private LessonController lessonController;
    public App () {
        // Initialize controllers
        learnerController = new LearnerController();
        coachController = new CoachController();
        lessonController = new LessonController();
    }

    public void start() {

    }
}
