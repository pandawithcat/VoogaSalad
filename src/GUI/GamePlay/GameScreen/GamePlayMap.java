package GUI.GamePlay.GameScreen;

import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

public class GamePlayMap extends HBox {

    public GamePlayMap(double width, double height){
        setPrefWidth(width);
        setPrefHeight(height);
        setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: blue;");
    }
}
