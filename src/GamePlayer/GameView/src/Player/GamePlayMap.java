package Player;

import BackendExternal.Logic;
import Configs.TransferImageView;
import javafx.scene.Group;
import javafx.scene.layout.*;

import java.util.List;

public class GamePlayMap extends GridPane {
    private Logic myLogic;
    private List <TransferImageView> terrainList;
    private Group mapRoot;

    public GamePlayMap(double width, double height, Logic logic) {
        myLogic = logic;
        terrainList = myLogic.getLevelTerrain();
        mapRoot = new Group();
        //TODO: fix what the getLevelTerrain() method returns - we need objects that can be added to root
        terrainList.forEach(terrainObject -> mapRoot.getChildren().addAll(terrainObject));
        setPrefWidth(width);
        setPrefHeight(height);
    }

}
