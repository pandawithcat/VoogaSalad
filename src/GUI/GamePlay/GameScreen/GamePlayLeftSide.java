package GUI.GamePlay.GameScreen;

import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GamePlayLeftSide extends VBox {
    public GamePlayMap myMap;
    public GamePlaySettingsBar mySettings;

    public GamePlayLeftSide(double width, double height){
        setPrefWidth(width * 3/ 4);
        setPrefHeight(height);
        myMap = new GamePlayMap(width,height * 9 / 10);
        mySettings = new GamePlaySettingsBar(width,height / 10);
        this.getChildren().addAll(myMap, mySettings);
    }
}
