package Player.GamePlay.GamePlayLeft;

import BackendExternal.Logic;
import Player.GamePlay.EndLoopInterface;
import Player.GamePlay.SelectionInterface;
import javafx.scene.layout.VBox;

public class GamePlayLeftSide extends VBox {
    public static final double TOP_RATIO = 0.8;
    public static final double BOTTOM_RATIO = 0.2;
    public GamePlayMap myMap;
    public GamePlaySettingsBar mySettings;

    public GamePlayLeftSide(double width, double height, Logic logic, EndLoopInterface endLoop,
                            SelectionInterface home){
        setPrefWidth(width);
        setPrefHeight(height);
        myMap = new GamePlayMap(width,height * TOP_RATIO, logic, endLoop, home);
        mySettings = new GamePlaySettingsBar(width,height * BOTTOM_RATIO, logic);
        getChildren().addAll(myMap, mySettings);
    }

    public GamePlayMap getMap(){
        return myMap;
    }
}
