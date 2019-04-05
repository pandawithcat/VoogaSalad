package GUI.GamePlay.GameScreen;

import javafx.scene.layout.HBox;

public class GamePlayIDE extends HBox {
    public GamePlayLeftSide myGameLeft;
    public GamePlayRightSide myGameRight;
    public double screenWidth = 1000;
    private double screenHeight = 1000;
    private double screenMinX;
    private double screenMinY;

    public GamePlayIDE(){
        setPrefWidth(screenWidth);
        setPrefHeight(screenHeight);
        myGameLeft = new GamePlayLeftSide(screenWidth,screenHeight);
        myGameRight = new GamePlayRightSide(screenWidth,screenHeight);
        this.getChildren().addAll(myGameLeft,myGameRight);
    }

}
