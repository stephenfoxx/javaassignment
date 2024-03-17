public class Main {
    public static void main(String[] args) {

        /*
          - Create an instance of the application.
          - The application is designed using the MVC (Model-View-Controller) design pattern.
          - Model represents real-life objects.
          - Controller holds the instances of their respective models and handles operations on the models.
          - View is responsible for printing client menus and retrieving user inputs.
         */
        App app = new App();

        // Call the start method on the application to begin execution.
        app.start();
    }
}