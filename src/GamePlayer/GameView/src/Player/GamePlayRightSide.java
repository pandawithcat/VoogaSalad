package Player;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class GamePlayRightSide extends VBox {

    public static final double ARSENAL_RATIO = 0.75;
    public static final double BUTTON_RATIO = 0.25;
    private GamePlayArsenal myGameArsenal;
    private ButtonPanel myButtonPanel;



    public GamePlayRightSide(double width, double height, Logic logic, PlayInterface method){
        setPrefWidth(width);
        setPrefHeight(height);
        try {
            myGameArsenal = new GamePlayArsenal(width, height * ARSENAL_RATIO, logic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.getChildren().addAll(myGameArsenal, createButtonPanel(width, height, method));
    }

    private VBox createButtonPanel(double width, double height, PlayInterface method){
        myButtonPanel = new ButtonPanel(width, height * BUTTON_RATIO, method);
        return myButtonPanel;
    }

}
