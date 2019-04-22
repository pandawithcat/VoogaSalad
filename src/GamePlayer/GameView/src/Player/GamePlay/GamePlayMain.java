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

import static Player.GamePlay.GamePlayIDE.LEFT_RATIO;
import static Player.GamePlay.GamePlayLeft.GamePlayLeftSide.TOP_RATIO;

public class GamePlayMain extends Application {
    private String Title = "VoogaSalad Game";
    private String GAME_MUSIC = "resources/gameMusic.mp3";
    public static long FRAMES_PER_SECOND = 60;
    private static final Paint backgroundColor = Color.NAVY;
    private double screenWidth = ScreenSize.getWidth();
    private double screenHeight = ScreenSize.getHeight();
    private static final int padding = 15;
    private Logic myLogic = new Logic();
    private GamePlayIDE myGameIDE;
    private Group root;
    private MediaPlayer mediaPlayer;
    @Override
    public void start(Stage stage){
        try {
            Stage primaryStage = stage;
            root = new Group();
            primaryStage.setX(screenWidth);
            primaryStage.setY(screenHeight);
            var startScreen = new Scene(root, screenWidth, screenHeight,backgroundColor);
            startScreen.getStylesheets().add("gameplay.css");
            MediaView music = createWelcomeMusic();
            root.getChildren().add(music);
            myGameIDE = new GamePlayIDE(myLogic, () -> startLoop(), () -> fastFoward(), root, stage, mediaPlayer);
            root.getChildren().add(myGameIDE);
            primaryStage.setScene(startScreen);
            primaryStage.setTitle(Title);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setGameInfo(GameInfo gameInfo){
        myLogic.createGameInstance(gameInfo, screenWidth*LEFT_RATIO, screenHeight* TOP_RATIO);
    }
    private void fastFoward(){
        FRAMES_PER_SECOND = 150;
    }

    private MediaView createWelcomeMusic(){
        Media sound = new Media(new File(GAME_MUSIC).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        return mediaView;
    }

    private void startLoop(){
        long prevTime = System.currentTimeMillis();
        while(true){
            long currTime = System.currentTimeMillis();
            step(currTime-prevTime);
//            prevTime = currTime;
        }
    }

    private void step(long elapsedTime){
        //TODO: yeah idk if this is best design below
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
