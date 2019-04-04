package GUI.GamePlay.GameScreen;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GamePlayVisualization {
    private String Title = "VoogaSalad Game";
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
//    private static final Paint backgroundColor = Color.WHITE;
    private double screenWidth;
    private double screenHeight;
    private double screenMinX;
    private double screenMinY;

    public void start(Stage stage){
        Stage primaryStage = stage;
        var root = new Group();
        var startScreen = new Scene(root, screenWidth, screenHeight);
        //TODO:should this be global below?
        GamePlayIDE myGameIDE = new GamePlayIDE();
        root.getChildren().add(myGameIDE);
        primaryStage.setScene(startScreen);
        primaryStage.setTitle(Title);
        primaryStage.show();
    }

}
