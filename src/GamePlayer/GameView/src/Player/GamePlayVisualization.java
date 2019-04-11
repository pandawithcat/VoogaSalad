package Player;

import BackendExternal.Logic;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class GamePlayVisualization extends Application {
    private String Title = "VoogaSalad Game";
    private String GAME_MUSIC = "resources/gameMusic.mp3";
    private static final long FRAMES_PER_SECOND = 1;
    private static final long MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final long SECOND_DELAY = 1 / FRAMES_PER_SECOND;
    private static final Paint backgroundColor = Color.NAVY;
    private double screenWidth;
    private double screenHeight;
    private static final int padding = 15;
    private Logic myLogic;
    private Timeline animation = new Timeline();
    private GamePlayIDE myGameIDE;

    @Override
    public void start(Stage stage){
        try {
            Stage primaryStage = stage;
            var root = new Group();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setX(screenWidth);
            primaryStage.setY(screenHeight);
            screenWidth = primScreenBounds.getWidth();
            screenHeight = primScreenBounds.getHeight();
            var startScreen = new Scene(root, screenWidth, screenHeight,backgroundColor);
            startScreen.getStylesheets().add("gameplay.css");

//            myLogic = new Logic();
            PlayInterface playMethod = () -> startLoop();
            myGameIDE = new GamePlayIDE(screenWidth, screenHeight, myLogic, playMethod);
            root.getChildren().add(myGameIDE);
            primaryStage.setScene(startScreen);
            primaryStage.setTitle(Title);
            primaryStage.show();
            MediaView music = createWelcomeMusic();
            root.getChildren().add(music);
            //gameLoop
            startLoop();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private MediaView createWelcomeMusic(){
        Media sound = new Media(new File(GAME_MUSIC).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        return mediaView;
    }


    private void startLoop(){
        System.out.println("yes boy");
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }

    private void step(long elapsedTime){
//        myLogic.update(elapsedTime);
//        if (myLogic.checkIfLevelEnd()){
//            myLogic.
        //TODO: yeah idk if this is best design below
//        myGameIDE.getLeft().getMap().update(elapsedTime);
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
