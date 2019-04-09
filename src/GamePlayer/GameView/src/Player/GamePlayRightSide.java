package Player;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GamePlayRightSide extends VBox {

    public GamePlayArsenal myGameArsenal;
    public double rightSideWidth;
    private double rightSideHeight;
    private static final int padding = 10;
    private double screenMinX;
    private double screenMinY;

    public GamePlayRightSide(double width, double height){
        setPrefWidth(width);
        setPrefHeight(height);
        myGameArsenal = new GamePlayArsenal(width, height * 9 / 10);
        this.getChildren().addAll(myGameArsenal, createPlayButton(width, height));
        setPadding(new Insets(padding,padding,padding,padding));
        setSpacing(padding);

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