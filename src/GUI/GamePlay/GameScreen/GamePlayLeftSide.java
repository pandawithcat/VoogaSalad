package GUI.GamePlay.GameScreen;

import javafx.scene.layout.VBox;

public class GamePlayLeftSide extends VBox {
    public GamePlayMap myMap;
    public GamePlaySettingsBar mySettings;

    public GamePlayLeftSide(double width, double height){
        setPrefWidth(width * 3/ 4);
        setPrefHeight(height);
    }
}
