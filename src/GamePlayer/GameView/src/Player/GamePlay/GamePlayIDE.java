package Player.GamePlay;

import BackendExternal.Logic;
import Player.GamePlay.GamePlayLeft.GamePlayLeftSide;
import Player.GamePlay.GamePlayRight.GamePlayRightSide;
import Player.ScreenSize;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GamePlayIDE extends HBox {
    public static final double LEFT_RATIO = 0.75;
    public static final double RIGHT_RATIO = 0.25;
    public GamePlayLeftSide myGameLeft;
    public GamePlayRightSide myGameRight;

    public GamePlayIDE(Logic logic, PlayInterface method, PlayInterface fastFoward, EndLoopInterface endLoop,
                       SelectionInterface home,
                       Group root,
                       Stage stage,
                       MediaPlayer mediaPlayer){
        setPrefWidth(ScreenSize.getWidth());
        setPrefHeight(ScreenSize.getHeight());
        myGameLeft = new GamePlayLeftSide(ScreenSize.getWidth() * LEFT_RATIO, ScreenSize.getHeight(), logic, endLoop,
                home,
                stage);
        myGameRight = new GamePlayRightSide(ScreenSize.getWidth() * RIGHT_RATIO, ScreenSize.getHeight(), logic, method, fastFoward,
                myGameLeft.getMap(), root, stage, mediaPlayer);
        this.getChildren().addAll(myGameLeft,myGameRight);
    }
    public GamePlayLeftSide getLeft(){
        return myGameLeft;
    }

}
