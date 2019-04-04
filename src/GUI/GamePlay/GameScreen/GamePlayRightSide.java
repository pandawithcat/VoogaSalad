package GUI.GamePlay.GameScreen;

import javafx.scene.layout.VBox;

public class GamePlayRightSide extends VBox {

    public GamePlayArsenal myGameArsenal;
    public GamePlayButton play;

    public double rightSideWidth;
    private double rightSideHeight;
    private double screenMinX;
    private double screenMinY;

    public GamePlayRightSide(double width, double height){
        rightSideWidth = width / 4;
        rightSideHeight = height;
        setPrefWidth(rightSideWidth);
        setPrefHeight(height);
        myGameArsenal = new GamePlayArsenal(rightSideWidth, rightSideHeight);
        play = new GamePlayButton();
        this.getChildren().addAll(myGameArsenal,play);
    }

}
