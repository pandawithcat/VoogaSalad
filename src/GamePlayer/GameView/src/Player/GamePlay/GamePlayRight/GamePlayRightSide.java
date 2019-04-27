package Player.GamePlay.GamePlayRight;

import BackendExternal.Logic;
import Player.GamePlay.GamePlayLeft.ButtonPanel;
import Player.GamePlay.GamePlayLeft.GamePlayArsenal;
import Player.GamePlay.GamePlayLeft.GamePlayMap;
import Player.GamePlay.PlayInterface;
import Player.GamePlay.SelectionInterface;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;

public class GamePlayRightSide extends VBox {

    public static final double ARSENAL_RATIO = 0.75;
    public static final double BUTTON_RATIO = 0.25;
    private GamePlayArsenal myGameArsenal;
    private ButtonPanel myButtonPanel;
    private MediaPlayer mediaPlayer;


    public GamePlayRightSide(double width, double height, Logic logic, PlayInterface method, PlayInterface fastFoward
            , GamePlayMap myMap, Group root, SelectionInterface home, MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
        setPrefWidth(width);
        setPrefHeight(height);
        try {
            myGameArsenal = new GamePlayArsenal(width, height * ARSENAL_RATIO, logic, myMap, root);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.getChildren().addAll(myGameArsenal, createButtonPanel(width, height, method, fastFoward, home));
    }
    public void update(){
        myGameArsenal.recreateArsenal();
    }

    private VBox createButtonPanel(double width, double height, PlayInterface method, PlayInterface fastFoward, SelectionInterface home){
        myButtonPanel = new ButtonPanel(width, height * BUTTON_RATIO, method, fastFoward, home, mediaPlayer);
        return myButtonPanel;
    }

}
