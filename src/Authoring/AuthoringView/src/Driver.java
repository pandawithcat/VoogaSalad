import GameAuthoringEnvironment.AuthoringScreen.AuthoringVisualization;
import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {

    AuthoringVisualization VogaSalad = new AuthoringVisualization();
    /*
    Starts the Application
     */

    public void start (Stage stage) {
        VogaSalad.start(stage);
        stage.show();
    }
    public static void main (String[] args) {
        Application.launch(args);
    }
}
