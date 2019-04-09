package Player;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class GamePlayRightSide extends VBox {

    public static final double ARSENAL_RATIO = 0.80;
    public static final double BUTTON_RATIO = 0.20;
    public GamePlayArsenal myGameArsenal;


    public GamePlayRightSide(double width, double height, Logic logic){
        setPrefWidth(width);
        setPrefHeight(height);
        myGameArsenal = new GamePlayArsenal(width, height * ARSENAL_RATIO, logic);
        this.getChildren().addAll(myGameArsenal, createPlayButton(width, height));

    }

    private Button createPlayButton(double width, double height){
        Button play = new Button("Play");
        play.setPrefWidth(width);
        play.setPrefHeight(height * BUTTON_RATIO);
        play.setOnAction(e -> System.out.println("method to play"));
        return play;
    }

}
