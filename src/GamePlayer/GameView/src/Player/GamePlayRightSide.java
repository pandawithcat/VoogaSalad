package Player;

import BackendExternal.Logic;
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

    public GamePlayRightSide(double width, double height, Logic logic){
        setPrefWidth(width);
        setPrefHeight(height);
        myGameArsenal = new GamePlayArsenal(width, height * 9 / 10, logic);
        this.getChildren().addAll(myGameArsenal, createPlayButton(width, height));

    }



    private Button createPlayButton(double width, double height){
        Button play = new Button("Play");
        play.setPrefWidth(width);
        play.setPrefHeight(height/10);
        play.setOnAction(e -> System.out.println("method to play"));
        return play;
    }

}
