package GUI.GamePlay.GameScreen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GamePlayRightSide extends VBox {

    public GamePlayArsenal myGameArsenal;
    public double rightSideWidth;
    private double rightSideHeight;
    private static final int padding = 15;
    private double screenMinX;
    private double screenMinY;

    public GamePlayRightSide(double width, double height){
        rightSideWidth = width / 4;
        rightSideHeight = height;
        setPrefWidth(rightSideWidth);
        setPrefHeight(rightSideHeight);
        myGameArsenal = new GamePlayArsenal(rightSideWidth, rightSideHeight * 9 / 10);
        this.getChildren().addAll(myGameArsenal, createPlayButton(rightSideWidth, rightSideHeight));
    }

    private Button createPlayButton(double width, double height){
        Button play = new Button("Play");
        play.setPrefWidth(width);
        play.setPrefHeight(height/10);
        play.setOnAction(e -> System.out.println("method to play"));
        setPadding(new Insets(padding,padding,padding,padding));
        return play;
    }

}
