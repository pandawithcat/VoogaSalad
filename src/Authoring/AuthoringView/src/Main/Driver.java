import GameAuthoringEnvironment.AuthoringScreen.AuthoringVisualization;
import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

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
        launch(args);
    }
}
