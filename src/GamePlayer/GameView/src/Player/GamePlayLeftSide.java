package Player;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class GamePlayLeftSide extends VBox {
    public GamePlayMap myMap;
    public GamePlaySettingsBar mySettings;
    public static final int padding = 10;

    public GamePlayLeftSide(double width, double height, Logic logic){
        setPrefWidth(width);
        setPrefHeight(height);
        myMap = new GamePlayMap(width,height * 9 / 10, logic);
        mySettings = new GamePlaySettingsBar(width,height * 2/ 10);
        this.getChildren().addAll(myMap, mySettings);
    }

    public GamePlayMap getMap(){
        return myMap;

    }
}
