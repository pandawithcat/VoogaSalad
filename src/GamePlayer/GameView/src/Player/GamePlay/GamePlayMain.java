package Player.GamePlay;

import BackendExternal.GameInfo;
import BackendExternal.Logic;
import Player.ScreenSize;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class GamePlayMain extends Application {
    private String Title = "VoogaSalad Game";
    private String GAME_MUSIC = "resources/gameMusic.mp3";
    public static int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint backgroundColor = Color.NAVY;
    private double screenWidth = ScreenSize.getWidth();
    private double screenHeight = ScreenSize.getHeight();
    private static final int padding = 15;
    private Logic myLogic;
    private Timeline animation = new Timeline();
    private GamePlayIDE myGameIDE;
    private Group root;
    @Override
    public void start(Stage stage){
        try {
            Stage primaryStage = stage;
            root = new Group();
            myLogic = new Logic();
            primaryStage.setX(screenWidth);
            primaryStage.setY(screenHeight);
            var startScreen = new Scene(root, screenWidth, screenHeight,backgroundColor);
            startScreen.getStylesheets().add("gameplay.css");
            myGameIDE = new GamePlayIDE(screenWidth, screenHeight, myLogic, () -> startLoop(), () -> fastFoward());
            root.getChildren().add(myGameIDE);
            primaryStage.setScene(startScreen);
            primaryStage.setTitle(Title);
            primaryStage.show();
            MediaView music = createWelcomeMusic();
            root.getChildren().add(music);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setGameInfo(GameInfo gameInfo){
        myLogic.createGameInstance(gameInfo);
    }
    private void fastFoward(){
        FRAMES_PER_SECOND = 150;
    }

    private MediaView createWelcomeMusic(){
        Media sound = new Media(new File(GAME_MUSIC).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        return mediaView;
    }

    private void startLoop(){
        FRAMES_PER_SECOND = 60;
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step(double elapsedTime){
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
        myGameIDE.getLeft().getMap().update(elapsedTime);
    }
}
