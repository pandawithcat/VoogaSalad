package GUI.GamePlay.GameScreen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AvailableGamesDisplay extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        StackPane root = new StackPane();
        root.setId("pane");
        root.applyCss();
        root.layout();
        var scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public Stage getStage(){
        return stage;
    }
}
