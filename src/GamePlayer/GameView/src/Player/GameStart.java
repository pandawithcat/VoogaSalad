package Player;

import BackendExternal.GameInfo;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameStart extends Application {
    private Stage stage;
    private Group root;
    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        root = new Group();
        root.setId("pane");
        var scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setTitle(GameInfo gameInfo){

    }


}
