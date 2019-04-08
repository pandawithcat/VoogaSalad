package Player;

import BackendExternal.GameInfo;
import BackendExternal.Logic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.List;

public class GameSelection extends Application {
    private StackPane root;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new StackPane();
        root.setId("pane");
        var scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//    private List<GameInfo> uploadAvailableGames(){
//        Logic logic = new Logic();
//        List<GameInfo> gameInfoList= logic.getGameOptions();
//        return gameInfoList;
//    }
}
