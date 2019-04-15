package Player.GamePlay;

import BackendExternal.Logic;
import Player.GamePlay.GamePlayLeft.GamePlayLeftSide;
import Player.GamePlay.GamePlayRight.GamePlayRightSide;
import Player.GamePlay.PlayInterface;
import javafx.scene.layout.HBox;

public class GamePlayIDE extends HBox {
    public static final double LEFT_RATIO = 0.75;
    public static final double RIGHT_RATIO = 0.25;
    public GamePlayLeftSide myGameLeft;
    public GamePlayRightSide myGameRight;

    public GamePlayIDE(double width, double height, Logic logic, PlayInterface method, PlayInterface fastFoward){
        setPrefWidth(width);
        setPrefHeight(height);
        myGameLeft = new GamePlayLeftSide(width * LEFT_RATIO, height, logic);
        myGameRight = new GamePlayRightSide(width * RIGHT_RATIO, height, logic, method, fastFoward);
        this.getChildren().addAll(myGameLeft,myGameRight);
    }
    public GamePlayLeftSide getLeft(){
        return myGameLeft;
    }

}
