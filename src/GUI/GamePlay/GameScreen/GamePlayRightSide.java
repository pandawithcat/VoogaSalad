package GUI.GamePlay.GameScreen;

import javafx.scene.layout.VBox;
import java.awt.*;

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
        this.getChildren().addAll(myGameArsenal, play);
    }

    private Button createPlayButton(){
        Button play = new Button();
        play.setOnAction(e -> System.out.println("method to play"));
        return play;
    }

}
