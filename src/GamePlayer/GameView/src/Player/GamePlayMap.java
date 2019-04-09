package Player;

import BackendExternal.Logic;
import Configs.TransferImageView;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GamePlayMap extends GridPane {
    private Logic myLogic;
    private List<TransferImageView> terrainList;
    private Group mapRoot;

    public GamePlayMap(double width, double height, Logic logic) {
        myLogic = logic;
//        terrainList = myLogic.getLevelTerrain();
        mapRoot = new Group();
        setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        //TODO: not sure if this works yet
//        terrainList.forEach(terrainNode -> mapRoot.getChildren().add(terrainNode));
        setPrefWidth(width);
        setPrefHeight(height);
    }

}
