package Player;

import BackendExternal.Logic;
import Configs.TransferImageView;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.*;

import java.util.List;

public class GamePlayMap extends GridPane {
    private Logic myLogic;
    private List terrainList;
    private Group mapRoot;

    public GamePlayMap(double width, double height, Logic logic) {
        myLogic = logic;
        terrainList = myLogic.getLevelTerrain();
        mapRoot = new Group();
        //TODO: not sure if this works yet
        terrainList.forEach(terrainNode -> mapRoot.getChildren().add((Node)terrainNode));
        setPrefWidth(width);
        setPrefHeight(height);
    }

}
