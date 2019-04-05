package GUI.GamePlay.GameScreen;

import javafx.scene.layout.HBox;

public class GamePlayIDE extends HBox {
    public GamePlayLeftSide myGameLeft;
    public GamePlayRightSide myGameRight;

    private double screenMinX;
    private double screenMinY;

    public GamePlayIDE(double width, double height){
        setPrefWidth(width);
        setPrefHeight(height);
        myGameLeft = new GamePlayLeftSide(width,height);
        myGameRight = new GamePlayRightSide(width,height);
        this.getChildren().addAll(myGameLeft,myGameRight);
    }

}
