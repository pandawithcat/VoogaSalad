package GUI.GamePlay.GameScreen;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GamePlayRightSide extends VBox {

    public GamePlayArsenal myGameArsenal;
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
        this.getChildren().addAll(myGameArsenal, createPlayButton());
    }

    private Button createPlayButton(){
        Button play = new Button();
        play.setOnAction(e -> System.out.println("method to play"));
        return play;
    }

}
