package Player;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GamePlayRightSide extends VBox {

    public static final double ARSENAL_RATIO = 0.75;
    public static final double BUTTON_RATIO = 0.25;
    private GamePlayArsenal myGameArsenal;
    private ButtonPanel myButtonPanel;



    public GamePlayRightSide(double width, double height, Logic logic){
        setPrefWidth(width);
        setPrefHeight(height);
        myGameArsenal = new GamePlayArsenal(width, height * ARSENAL_RATIO, logic);
        this.getChildren().addAll(myGameArsenal, createButtonPanel(width, height));
    }

    private VBox createButtonPanel(double width, double height){
        myButtonPanel = new ButtonPanel(width, height * BUTTON_RATIO);
        return myButtonPanel;
    }

}
