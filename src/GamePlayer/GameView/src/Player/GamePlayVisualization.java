package Player;

import BackendExternal.Logic;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GamePlayVisualization extends Application {
    private String Title = "VoogaSalad Game";
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint backgroundColor = Color.NAVY;
    private double screenWidth = 1000;
    private double screenHeight = 600;
    private static final int padding = 15;
    private Logic myLogic;
    private Timeline animation = new Timeline();
    private GamePlayIDE myGameIDE;

    public GamePlayVisualization(Logic logic){
        myLogic = logic;
    }
    @Override
    public void start(Stage stage){
        try {
            Stage primaryStage = stage;
            var root = new Group();
            var startScreen = new Scene(root, screenWidth, screenHeight,backgroundColor);
            myGameIDE = new GamePlayIDE(screenWidth, screenHeight, myLogic);
            myGameIDE.setPadding(new Insets(padding,padding,padding,padding));
            root.getChildren().add(myGameIDE);
            primaryStage.setScene(startScreen);
            primaryStage.setTitle(Title);
            primaryStage.show();

            //gameLoop
            var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.getKeyFrames().add(frame);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void step(double elapsedTime){
        myLogic.update(elapsedTime);
        myLogic.checkIfLevelEnd();
        //TODO: if the level end is true stop the game loop
        //TODO: dynamically update views with methods below
        //TODO: changelistener for dragging objects
        //TODO: render method
        //TODO: dynamically update views with methods below
        //TODO: change all the scores and lives and
        //getViewsToBeAdded
        //getRemovedImageViews
        //
    }


    public static void main (String[] args) {
        Application.launch(args);
    }

}
