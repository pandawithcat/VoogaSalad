package GUI.GamePlay.GameScreen;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    @Override
    public void start(Stage primaryStage) {
        LoadingSplashScreen loadingSplashScreen = new LoadingSplashScreen();
        loadingSplashScreen.start(new Stage());
        stage = loadingSplashScreen.getStage();
        stage.show();
    }

}
